package org.example.hexlet;

import io.javalin.Javalin;
import org.example.hexlet.dto.CoursesPage;
import org.example.hexlet.dto.UsersPage;
import org.example.hexlet.pseudoDatabases.CoursesList;
import org.example.hexlet.pseudoDatabases.UsersList;

import java.util.Collections;

public class HelloWorld {
    public static Javalin getApp() {
        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/courses", ctx -> {
            // Usually get from database here
            var courses = new CoursesList().getCourses();
            var page = new CoursesPage(courses);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
           var users = new UsersList().getUsers();
           var page = new UsersPage(users);
           ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        return app;
    }

//    public static Course getCourse(String id) {
//        var courses = getCoursesList();
//        return courses.stream()
//                .filter(course -> course.getId().equals(Long.parseLong(id)))
//                .findFirst()
//                .orElse(null);
//    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
