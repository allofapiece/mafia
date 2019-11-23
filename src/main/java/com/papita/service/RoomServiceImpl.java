package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;
import com.papita.entity.dto.UserDto;
import com.papita.repository.RoomRepository;
import com.papita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.util.List;

/**
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;


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
    public User join(Long roomId, String username, Session session) {
        //TODO process not found room
        Room room = roomRepository.findById(roomId).get();
        User user = userService.createUser(username, session);

        room.addUser(user);
        roomRepository.saveAndFlush(room);

        return user;
    }
}
