package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;

import javax.websocket.Session;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @version 1.0.0
 */
public interface BroadCaster {
    void broadcast(Session session, Object object);

    void broadcast(Session session, Object object, Predicate<? super Session> predicate);

    void broadcast(Session session, Object object, Set<User> users);

    void broadcast(Session session, Object object, Room room);

    void sendForSession(Session session, Object object);
}
