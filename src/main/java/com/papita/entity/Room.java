package com.papita.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    public Room(int usersAmount) {
        this.usersAmount = usersAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int usersAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();


    public void addUser(User user) {
        this.users.add(user);
    }
}
