package com.papita.controller;

import com.papita.entity.dto.RoomDto;
import com.papita.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0.0
 */
@RequestMapping("/api/rooms")
@RestController()
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
