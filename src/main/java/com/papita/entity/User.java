package com.papita.entity;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 * @version 1.0.0
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    public User(String username, Long roomId, String sessionId) {
        this.username = username;
        this.roomId = roomId;
        this.sessionId = sessionId;
    }


    public User(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CITIZEN;

    @Expose(serialize = false, deserialize = false)
    private String sessionId;

    private boolean isAlive;

    private String username;

    private Long roomId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT;
}
