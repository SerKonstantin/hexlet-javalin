package org.example.hexlet.pseudoDatabases.users;

import org.example.hexlet.model.User;

public class GenerateUsers {
    public static void generate() {
        UsersRepository.save(new User("Kosta", "Srebro", "kosta@gmail.com", "123"));
        UsersRepository.save(new User("John", "Smith", "smith@gmail.com", "123"));
        UsersRepository.save(new User("John", "Snow", "bastardo@castle.com", "123"));
        UsersRepository.save(new User("Ann", "Hatt", "annie@yahoo.com", "123"));
    }
}
