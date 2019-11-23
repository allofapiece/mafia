package com.papita.controller.ws;

import com.papita.config.WebSocketConfigurator;
import com.papita.entity.User;
import com.papita.service.BroadCaster;
import com.papita.service.RoomService;
import com.papita.service.SessionHolder;
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
@ServerEndpoint(value = "/room/{roomId}/join/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class, configurator = WebSocketConfigurator.class)
public class RoomWSController {
    private final RoomService roomService;

    private final BroadCaster broadCaster;

    private final SessionHolder sessionHolder;

    @OnOpen
    public void onOpen(@PathParam("roomId") Long roomId, @PathParam("username") String username, Session session) throws IOException, EncodeException {
        User user = roomService.join(roomId, username, session);
        sessionHolder.addSession(session);
        broadCaster.broadcast(session, new Message(roomService.get(roomId).getUsers()), roomService.get(roomId));
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
