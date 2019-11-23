package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SimpleBroadCaster implements BroadCaster {
    private final SessionHolder sessionHolder;

    public void broadcast(Session session, Object object) {
        sessionHolder.getSessions()
                .entrySet()
                .stream()
                .forEach(openSession -> sendForSession(openSession.getValue(), object));
    }

    public void broadcast(Session session, Object object, Predicate<? super Session> predicate) {
        sessionHolder.getSessions()
                .values()
                .stream()
                .filter(predicate)
                .forEach(openSession -> sendForSession(openSession, object));
    }

    public void broadcast(Session session, Object object, Set<User> users) {
        broadcast(session, object, (openSession) -> users.stream()
                .map(User::getSessionId)
                .collect(Collectors.toList())
                .contains(openSession.getId()));
    }

    public void broadcast(Session session, Object object, Room room) {
        broadcast(session, object, room.getUsers());
    }

    public void sendForSession(Session session, Object object) {
        try {
            session.getBasicRemote().sendObject(object);
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }
}
