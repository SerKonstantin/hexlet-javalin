package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.pseudoDatabases.courses.CoursesRepository;
import org.example.hexlet.util.NamedRoutes;

import java.util.Collections;
import java.util.List;

public class CoursesController {
    public static void index(Context ctx) {
        var term = ctx.queryParam("term");
        List<Course> courses;

        if (term != null) {
            courses = CoursesRepository.search(term);
        } else {
            courses = CoursesRepository.getCourses();
        }

        var page = new CoursesPage(courses, term);
        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();

            var course = CoursesRepository.findById(id);
            if (course == null) {
                throw new NotFoundResponse("Course not found");
            }

            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        } catch (ValidationException e) {
            ctx.status(404).result("Page not found");
        }
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name").trim();

        try {
            var description = ctx.formParamAsClass("description", String.class)
                    .check(value -> value.length() >= 8, "Description is too short")
                    .get();
            var course = new Course(name, description);
            CoursesRepository.save(course);
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var description = ctx.formParam("description");
            var page = new BuildCoursePage(name, description, e.getErrors());
            ctx.render("courses/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }
}
