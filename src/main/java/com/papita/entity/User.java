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
    public User(String username, String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose(serialize = false, deserialize = false)
    private String sessionId;

    private String username;

    @Enumerated(EnumType.STRING)
    private Status status = Status.WAIT;
}
