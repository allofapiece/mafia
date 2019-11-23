package com.papita.service;

import com.papita.entity.Status;
import com.papita.entity.User;

import javax.websocket.Session;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0.0
 */
public interface UserService {
    User save(User user);

    Optional<User> get(Long id);

    User createUser(String username, Long roomId, Session session);

    void setStatusForUser(Long id, Status status);

    boolean isAllReady(Long roomId);

    void setMafias(List<Long> ids);
}
