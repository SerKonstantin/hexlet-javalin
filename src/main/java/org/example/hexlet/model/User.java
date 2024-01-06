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
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    public User(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }
}
