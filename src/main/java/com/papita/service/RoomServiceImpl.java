package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;
import com.papita.repository.RoomRepository;
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
public class RoomServiceImpl implements RoomService {

    private final UserService userService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private static final int RATIO = 3;

    @Override
    public Room createRoom(RoomDto roomDto) {
        return roomRepository.save(new Room(roomDto.getUsersAmount()));
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room get(Long id) {
        return roomRepository.getById(id);
    }

    @Override
    public User join(Long roomId, Long userId, Session session) {
        //TODO process not found room
        Room room = roomRepository.findById(roomId).get();
        User user = userService.get(userId);
        user.setSessionId(session.getId());

        room.addUser(user);

        roomRepository.saveAndFlush(room);

        return user;
    }

    @Override
    public User unjoin(Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId).get();
        User user = userService.get(userId);
        room.getUsers().remove(user);

        roomRepository.saveAndFlush(room);

        return user;
    }

    @Override
    public int mafiasAmount(Long roomId) {
        Room room = roomRepository.findById(roomId).get();

        return (int) Math.floor(room.getUsersAmount() / (RATIO + 1));
    }
}
