package com.papita.controller.ws;

import com.papita.config.WebSocketConfigurator;
import com.papita.entity.Room;
import com.papita.entity.Status;
import com.papita.entity.User;
import com.papita.service.BroadCaster;
import com.papita.service.RoomService;
import com.papita.service.SessionHolder;
import com.papita.service.UserService;
import com.papita.service.converter.MessageDecoder;
import com.papita.service.converter.MessageEncoder;
import com.papita.websockets.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

import static com.papita.util.StreamUtil.lastN;

@Component
@RequiredArgsConstructor
@ServerEndpoint(value = "/room/{roomId}/ready/{id}", decoders = MessageDecoder.class, encoders = MessageEncoder.class, configurator = WebSocketConfigurator.class)
public class RoomReadyWSController {
    private final RoomService roomService;

    private final UserService userService;

    private final BroadCaster broadCaster;

    private final SessionHolder sessionHolder;

    @OnOpen
    public void onOpen(@PathParam("roomId") Long roomId, @PathParam("id") Long id, Session session) throws IOException, EncodeException {
        sessionHolder.addSession(session);
        User user1 = userService.get(id);
        user1.setSessionId(session.getId());
        userService.save(user1);
        userService.setStatusForUser(id, Status.READY);

        if (userService.isAllReady(roomId)) {
            int amount = roomService.mafiasAmount(roomId);

            Room room = roomService.get(roomId);

            SortedMap<Double, Long> map = new TreeMap<>();
            for (User user : room.getUsers()) {
                map.put(Math.random(), user.getId());
            }

            List<Long> ids = map.values().stream().collect(lastN(amount));
            userService.setMafias(ids);

            for (User user: room.getUsers()) {
                broadCaster.sendForSession(sessionHolder.getSession(user.getSessionId()), new Message(user));
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        System.out.println("e");
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        sessionHolder.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessionHolder.remove(session.getId());
    }
}
