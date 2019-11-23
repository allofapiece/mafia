package com.papita.repository;

import com.papita.entity.Room;
import com.papita.entity.RoomProjection;
import com.papita.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 */
@Repository
@RepositoryRestResource(excerptProjection = RoomProjection.class, collectionResourceRel = "rooms", path = "rooms")
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room getById(Long id);
}
