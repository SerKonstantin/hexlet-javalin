package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.CoursesPage;
import org.example.hexlet.dto.UserPage;
import org.example.hexlet.dto.UsersPage;
import org.example.hexlet.pseudoDatabases.CoursesList;
import org.example.hexlet.pseudoDatabases.UsersList;

import java.util.Collections;
import java.util.stream.Collectors;

public class HelloWorld {
    public static Javalin getApp() {
        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/courses", ctx -> {
            var courses = new CoursesList().getCourses();
            var term = ctx.queryParam("term");
            if (term != null) {
                var filteredCourses = courses.stream()
                        .filter(c -> c.getName().toLowerCase().contains(term.toLowerCase()))
                        .collect(Collectors.toList());
                var page = new CoursesPage(filteredCourses);
                ctx.render("courses/index.jte", Collections.singletonMap("page", page));
            } else {
                var page = new CoursesPage(courses);
                ctx.render("courses/index.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/users", ctx -> {
           var users = new UsersList().getUsers();
           var page = new UsersPage(users);
           ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var users = new UsersList().getUsers();
            var user = users.stream()
                    .filter(u -> id.equals(u.getId()))
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }
            var page =new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
