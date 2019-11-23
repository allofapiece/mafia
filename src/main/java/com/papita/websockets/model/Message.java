package com.papita.websockets.model;

import com.papita.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Message {
    private User user;

    private Set<User> users;

    public Message(User user) {
        this.user = user;
    }

    public Message(Set<User> users) {
        this.users = users;
    }
}
