package com.papita.service;

import javax.websocket.Session;
import java.util.Map;

/**
 * @version 1.0.0
 */
public interface SessionHolder {
    void addSession(Session session);

    void remove(String id);

    Session getSession(String id);

    Map<String, Session> getSessions();

    boolean contains(String id);
}
