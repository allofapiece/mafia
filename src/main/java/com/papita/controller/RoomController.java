package com.papita.controller;

import com.papita.entity.dto.RoomDto;
import com.papita.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0.0
 */
@RestController("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public Response createRoom(@RequestBody RoomDto roomDto) {
        return new Response(roomService.createRoom(roomDto));
    }

    @GetMapping
    public Response getRooms() {
        return new Response(roomService.getRooms());
    }
}
