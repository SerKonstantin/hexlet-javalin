package org.example.hexlet.pseudoDatabases.users;

import org.example.hexlet.model.User;
import org.example.hexlet.util.DataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Imitate DB for instances of User class
public class UsersRepository {
    private static List<User> users = new ArrayList<>();

    // Private constructor to prevent init outside the class
    private UsersRepository() {}

    // Utility method to prefill DB
    public static void populate(int count) {
        var users = DataGenerator.getUsers(count);
        for (var user : users) {
            save(user);
        }
    }

    public static void save(User user) {
        if (user.getId() == null) {
            user.setId((long) users.size() + 1);
            users.add(user);
        }
    }

    public static List<User> search(String term) {
        return users.stream()
                .filter(u -> (u.getFirstName().toLowerCase().startsWith(term.toLowerCase()) ||
                        u.getSecondName().toLowerCase().startsWith(term.toLowerCase())))
                .collect(Collectors.toList());
    }

    public static Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public static Optional<User> findByNickname(String nickname) {
        return users.stream()
                .filter(user -> user.getNickname().toLowerCase().equals(nickname.toLowerCase()))
                .findFirst();
    }

    public static List<User> getUsers() {
        return users;
    }
}
