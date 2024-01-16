package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class User {
    private Long id;

    @ToString.Include
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String nickname, String firstName, String lastName, String email, String password) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
