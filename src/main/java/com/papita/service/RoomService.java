package com.papita.service;

import com.papita.entity.Room;
import com.papita.entity.User;
import com.papita.entity.dto.RoomDto;

/**
 * @version 1.0.0
 */
public interface RoomService {
    Room createRoom(RoomDto roomDto);
}
