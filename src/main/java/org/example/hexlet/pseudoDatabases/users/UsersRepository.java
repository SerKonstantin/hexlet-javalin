package org.example.hexlet.pseudoDatabases.users;

import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Imitate DB for instances of User class
public class UsersRepository {
    private static List<User> users = new ArrayList<>();

    // Private constructor to prevent init outside the class
    private UsersRepository() {}

    // Utility method to prefill DB
    public static UsersRepository populate() {
        GenerateUsers.generate();
        return new UsersRepository();
    }

    public static void save(User user) {
        user.setId((long) users.size() + 1);
        users.add(user);
    }

    public static List<User> search(String term) {
        return users.stream()
                .filter(u -> (u.getFirstName().toLowerCase().startsWith(term.toLowerCase()) ||
                        u.getSecondName().toLowerCase().startsWith(term.toLowerCase())))
                .collect(Collectors.toList());
    }

    public static User findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<User> getUsers() {
        return users;
    }
}
