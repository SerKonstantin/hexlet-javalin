package org.example.hexlet.pseudoDatabases.courses;

import org.example.hexlet.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Imitate DB for instances of Course class
public class CoursesRepository {
    private static List<Course> courses = new ArrayList<>();

    // Private constructor to prevent init outside the class
    private CoursesRepository() {}

    // Utility method to prefill DB
    public static CoursesRepository populate() {
        GenerateCourses.generate();
        return new CoursesRepository();
    }

    public static void save(Course course) {
        course.setId((long) courses.size() + 1);
        courses.add(course);
    }

    public static List<Course> search(String term) {
        return courses.stream()
                .filter(c -> c.getName().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static Course findById(Long id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Course> getCourses() { return courses; }
}
