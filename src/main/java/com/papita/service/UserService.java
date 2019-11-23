package com.papita.service;

import com.papita.entity.User;

import javax.websocket.Session;

/**
 * @version 1.0.0
 */
public interface UserService {
    User createUser(String username, Session session);
}
