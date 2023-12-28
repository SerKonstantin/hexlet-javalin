package org.example.hexlet.pseudoDatabases;

import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.List;

@Getter
public class UsersList {
    private List<User> users;

    public UsersList() {
        var user1 = new User("Kosta", "Srebro", "kosta@gmail.com");
        var user2 = new User("John", "Smith", "smith@gmail.com");
        var user3 = new User("John", "Snow", "bastardo@castle.com");
        user1.setId(1L);
        user2.setId(2L);
        user3.setId(3L);

        this.users = List.of(user1, user2, user3);
    }
}
