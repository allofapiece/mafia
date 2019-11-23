package com.papita.controller.ws;

import com.papita.config.WebSocketConfigurator;
import com.papita.entity.User;
import com.papita.service.BroadCaster;
import com.papita.service.RoomService;
import com.papita.service.SessionHolder;
import com.papita.service.UserService;
import com.papita.service.converter.*;
import com.papita.websockets.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@ServerEndpoint(value = "/room/{roomId}/join/{userId}", decoders = MessageDecoder.class, encoders = MessageEncoder.class, configurator = WebSocketConfigurator.class)
public class RoomWSController {
    private final RoomService roomService;

    private final UserService userService;

    private final BroadCaster broadCaster;

    private final SessionHolder sessionHolder;

    @OnOpen
    public void onOpen(@PathParam("roomId") Long roomId, @PathParam("userId") Long userId, Session session) throws IOException, EncodeException {
        User user = roomService.join(roomId, userId, session);
        sessionHolder.addSession(session);
        broadCaster.broadcast(session, new Message(roomService.get(roomId).getUsers()), roomService.get(roomId));
        broadCaster.broadcast(session, new Message(user));
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        System.out.println("e");
    }

    @OnClose
    public void onClose(@PathParam("roomId") Long roomId, @PathParam("userId") Long userId, Session session) throws IOException, EncodeException {
        User user = roomService.unjoin(roomId, userId);
        sessionHolder.remove(session.getId());
        broadCaster.broadcast(session, new Message(roomService.get(roomId).getUsers()), roomService.get(roomId));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessionHolder.remove(session.getId());
    }
}
