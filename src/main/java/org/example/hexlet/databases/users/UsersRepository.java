package org.example.hexlet.databases.users;

import org.example.hexlet.model.User;
import org.example.hexlet.databases.BaseRepository;
import org.example.hexlet.util.DataGenerator;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepository extends BaseRepository {

    // Utility method to prefill DB
    public static void populate(int count) throws SQLException {
        var users = DataGenerator.getUsers(count);
        for (var user : users) {
            save(user);
        }
    }

    public static void save(User user) throws SQLException {
        var sql = "INSERT INTO users (nickname, firstName, lastName, email, password) values (?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getNickname());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<User> search(String term) throws SQLException {
        var sql = "SELECT * FROM users WHERE LOWER(firstName) LIKE ? OR LOWER(secondName) LIKE ?";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + term.toLowerCase() + "%");
            stmt.setString(2, "%" + term.toLowerCase() + "%");
            var results = stmt.executeQuery();
            var users = new ArrayList<User>();
            while (results.next()) {
                var id = results.getLong("id");
                var nickname = results.getString("nickname");
                var firstName = results.getString("firstName");
                var lastName = results.getString("lastName");
                var email = results.getString("email");
                var password = results.getString("password");
                var user = new User(nickname, firstName, lastName, email, password);
                user.setId(id);
                users.add(user);
            }
            return users;
        }
    }

    public static Optional<User> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var results = stmt.executeQuery();
            if (results.next()) {
                var nickname = results.getString("nickname");
                var firstName = results.getString("firstName");
                var lastName = results.getString("lastName");
                var email = results.getString("email");
                var password = results.getString("password");
                var user = new User(nickname, firstName, lastName, email, password);
                user.setId(id);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }

        }
    }

    public static Optional<User> findByNickname(String nickname) throws SQLException {
        var sql = "SELECT * FROM users WHERE LOWER(nickname) = ?";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nickname.toLowerCase());
            var results = stmt.executeQuery();
            if (results.next()) {
                var id = results.getLong("id");
                var firstName = results.getString("firstName");
                var lastName = results.getString("lastName");
                var email = results.getString("email");
                var password = results.getString("password");
                var user = new User(nickname, firstName, lastName, email, password);
                user.setId(id);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }
    }

    public static List<User> getUsers() throws SQLException {
        var sql = "SELECT * FROM users";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            var results = stmt.executeQuery();
            var users = new ArrayList<User>();
            while (results.next()) {
                var id = results.getLong("id");
                var nickname = results.getString("nickname");
                var firstName = results.getString("firstName");
                var lastName = results.getString("lastName");
                var email = results.getString("email");
                var password = results.getString("password");
                var user = new User(nickname, firstName, lastName, email, password);
                user.setId(id);
                users.add(user);
            }
            return users;
        }
    }
}
