package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;
import com.papita.repository.RoomRepository;
import com.papita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final UserService userService;
    private final RoomRepository roomRepository;


    @Override
    public Room createRoom(RoomDto roomDto) {
        User user = userService.createUser(roomDto.getUsername());

        Room room = new Room(
                user,
                roomDto.getUsersAmount()
        );
        room.addUser(user);

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
}
