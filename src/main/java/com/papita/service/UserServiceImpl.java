package com.papita.service;

import com.papita.entity.Role;
import com.papita.entity.Status;
import com.papita.entity.User;
import com.papita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.List;

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
