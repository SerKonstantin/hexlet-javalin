package org.example.hexlet.pseudoDatabases.courses;

import org.example.hexlet.model.Course;

public class GenerateCourses {
    public static void generate() {
        CoursesRepository.save(new Course("SQL", "starter"));
        CoursesRepository.save(new Course("Java", "advanced"));
        CoursesRepository.save(new Course("Python", "unreachable"));
    }
}
