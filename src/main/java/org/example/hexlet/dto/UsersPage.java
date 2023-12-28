package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
}
