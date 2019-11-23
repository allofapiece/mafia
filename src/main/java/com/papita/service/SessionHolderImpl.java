package com.papita.service;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.0
 */
@Service
public class SessionHolderImpl implements SessionHolder {
    private Map<String, Session> sessions = new ConcurrentHashMap();

    public void addSession(Session session) {
        this.sessions.put(session.getId(), session);
    }

    @Override
    public void remove(String id) {
        sessions.remove(id);
    }

    public Session getSession(String id) {
        return this.sessions.get(id);
    }

    public Session getSession(Session session) {
        if (!sessions.containsKey(session.getId())) {
            addSession(session);
        }

        return getSession(session.getId());
    }

    public Map<String, Session> getSessions() {
        return this.sessions;
    }

    public boolean contains(String id) {
        return this.sessions.containsKey(id);
    }
}
