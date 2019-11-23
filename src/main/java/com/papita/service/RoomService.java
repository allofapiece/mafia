package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;
import com.papita.entity.dto.UserDto;

import javax.websocket.Session;
import java.util.List;

/**
 * @version 1.0.0
 */
public interface RoomService {
    Room createRoom(RoomDto roomDto);

    List<Room> getRooms();

    Room get(Long id);

    User join(Long roomId, String username, Session session);
}
