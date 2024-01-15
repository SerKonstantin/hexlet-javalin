package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.courses.EditCoursePage;
import org.example.hexlet.model.Course;
import org.example.hexlet.pseudoDatabases.courses.CoursesRepository;
import org.example.hexlet.util.NamedRoutes;

import java.util.Collections;
import java.util.List;

import static org.example.hexlet.pseudoDatabases.courses.CoursesRepository.findById;

public class CoursesController {
    public static void index(Context ctx) {
        // Search functionality
        var term = ctx.queryParam("term");
        List<Course> courses;
        if (term != null) {
            courses = CoursesRepository.search(term);
        } else {
            courses = CoursesRepository.getCourses();
        }

        // Pagination
        int perPage = 10;
        var currentPage =ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var pagesCount = (int) Math.ceil((double) courses.size() / perPage);

        var startIndex = (currentPage - 1) * perPage;
        var endIndex = Math.min(startIndex + perPage, courses.size());
        var paginatedCourses = courses.subList(startIndex, endIndex);

        // Add flash message
        var page = new CoursesPage(paginatedCourses, term, pagesCount, currentPage);
        page.setFlash(ctx.consumeSessionAttribute("flash"));

        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();

            var course = findById(id)
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));

            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        } catch (ValidationException e) {
            throw new NotFoundResponse("Page not found");
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
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.coursesPath());

        } catch (ValidationException e) {
            var description = ctx.formParam("description");
            var page = new BuildCoursePage(name, description, e.getErrors());
            ctx.render("courses/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }

    public static void edit(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CoursesRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));

            var name = course.getName();
            var description = course.getDescription();
            var page = new EditCoursePage(id, name, description, null);
            ctx.render("courses/edit.jte", Collections.singletonMap("page", page));
        } catch (ValidationException e) {
            throw new NotFoundResponse("Page not found");
        }
    }

    public static void update(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CoursesRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));

            var name = ctx.formParam("name").trim();

            try {
                var description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() >= 8, "Description is too short")
                        .get();

                course.setName(name);
                course.setDescription(description);

                ctx.redirect(NamedRoutes.coursesPath());

            } catch (ValidationException e) {
                var description = ctx.formParam("description");
                var page = new EditCoursePage(id, name, description, e.getErrors());
                ctx.render("courses/edit.jte", Collections.singletonMap("page", page)).status(422);
            }

        } catch (ValidationException e) {
            throw new NotFoundResponse("Page not found");
        }
    }
}
