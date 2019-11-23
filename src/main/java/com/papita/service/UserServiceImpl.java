package com.papita.service;

import com.papita.entity.Role;
import com.papita.entity.Status;
import com.papita.entity.User;
import com.papita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String username, Long roomId, Session session) {
        User user = new User(username, roomId, session.getId());
        userRepository.save(user);

        return user;
    }

    public User createUser(String username) {
        User user = new User(username);
        userRepository.save(user);

        return user;
    }

    @Override
    public void setStatusForUser(Long id, Status status) {
        User user = userRepository.findById(id).get();
        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public boolean isAllReady(Long roomId) {
        return !userRepository.existsUserByStatusAndRoomId(Status.WAIT, roomId);
    }

    @Override
    public void setMafias(List<Long> ids) {
        userRepository.updateUserSetRoleInIds(Role.MAFIA, ids);
    }
}
