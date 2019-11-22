package com.papita.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0.0
 */
@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Room {
    public Room(User user, int usersAmount) {
        this.user = user;
        this.usersAmount = usersAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    private int usersAmount;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        this.users.add(user);
    }
}
