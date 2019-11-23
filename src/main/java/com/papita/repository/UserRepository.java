package com.papita.repository;

import com.papita.entity.Role;
import com.papita.entity.Status;
import com.papita.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0.0
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByStatusAndRoomId(Status status, Long id);

    @Modifying
    @Query("update User u set u.role = :role where id in (:ids)")
    void updateUserSetRoleInIds(@Param("role") Role role, @Param("ids") List<Long> ids);
}
