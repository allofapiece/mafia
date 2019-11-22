package com.papita.repository;

import com.papita.entity.Role;
import com.papita.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends JpaRepository<Role, Long> {
}
