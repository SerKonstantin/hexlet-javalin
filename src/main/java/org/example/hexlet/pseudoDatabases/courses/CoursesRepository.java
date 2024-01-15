package org.example.hexlet.pseudoDatabases.courses;

import org.example.hexlet.model.Course;
import org.example.hexlet.pseudoDatabases.BaseRepository;
import org.example.hexlet.util.DataGenerator;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesRepository extends BaseRepository {
    // Utility method to prefill DB
    public static void populate(int count) throws SQLException {
        var courses = DataGenerator.getCourses(count);
        for (var course : courses) {
            save(course);
        }
    }

    public static void save(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Course> search(String term) throws SQLException {
        String sql = "SELECT * FROM courses WHERE LOWER(name) LIKE LOWER(?)";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, term + "%");
            var resultSet = stmt.executeQuery();
            var courses = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                courses.add(course);
            }
            return courses;
        }
    }

    public static Optional<Course> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM courses WHERE id = ?";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                return Optional.of(course);
            } else {
                return Optional.empty();
            }
        }
    }

    public static List<Course> getCourses() throws SQLException {
        var sql = "SELECT * FROM courses";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var courses = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                courses.add(course);
            }
            return courses;
        }
    }

    public static void update(Course course) throws SQLException {
        String sql = "UPDATE courses SET name = ?, description = ? WHERE id = ?";
        try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.setLong(3, course.getId());
            stmt.executeUpdate();
        }
    }
}
