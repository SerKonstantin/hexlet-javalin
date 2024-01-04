package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.Course;

@AllArgsConstructor
@Getter
public class CoursePage {
    private Course course;
}
