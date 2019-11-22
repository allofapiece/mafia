package com.papita.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * @version 1.0.0
 */
@Projection(
        name = "room",
        types = {Room.class})
public interface RoomProjection {
    Long getId();

    User getUser();

    int getUsersAmount();

    List<User> getUsers();
}
