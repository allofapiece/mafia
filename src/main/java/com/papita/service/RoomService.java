package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;

import javax.websocket.Session;
import java.util.List;

/**
 * @version 1.0.0
 */
public interface RoomService {
    Room createRoom(RoomDto roomDto);

    List<Room> getRooms();

    Room get(Long id);

    User join(Long roomId, Long userId, Session session);

    User unjoin(Long roomId, Long userId);

    int mafiasAmount(Long roomId);
}
