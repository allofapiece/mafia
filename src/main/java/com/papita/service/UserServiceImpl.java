package com.papita.service;

import com.papita.entity.User;
import com.papita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.websocket.Session;

/**
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(String username, Session session) {
        User user = new User(username, session.getId());
        userRepository.save(user);

        return user;
    }
}
