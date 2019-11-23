package com.papita.controller.ws;

import com.papita.config.WebSocketConfigurator;
import com.papita.entity.User;
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
import java.util.List;

@Component
@RequiredArgsConstructor
@ServerEndpoint(value = "/mafiaturn/join/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class, configurator = WebSocketConfigurator.class)
public class MafiaTurnWSController {

    private final SessionHolder sessionHolder;
    private final UserService userService;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {

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
