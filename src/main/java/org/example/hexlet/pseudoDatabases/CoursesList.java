package org.example.hexlet.pseudoDatabases;

import lombok.Getter;
import org.example.hexlet.model.Course;

import java.util.List;

@Getter
public class CoursesList {
    private List<Course> courses;

    public CoursesList() {
        var course1 = new Course("SQL", "starter");
        var course2 = new Course("Java", "advanced");
        var course3 = new Course("Python", "unreachable");
        course1.setId(1L);
        course2.setId(2L);
        course3.setId(3L);

        this.courses = List.of(course1, course2, course3);
    }
}
