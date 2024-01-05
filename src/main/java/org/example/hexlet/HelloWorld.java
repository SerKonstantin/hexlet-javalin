package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.pseudoDatabases.CoursesList;
import org.example.hexlet.pseudoDatabases.users.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {
    private static final List<Course> COURSES = new CoursesList().getCourses();
    public static final List<User> USERS = UsersRepository.populate().getUsers();

    public static Javalin getApp() {
        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            List<Course> courses;
            if (term != null) {
                courses = COURSES.stream()
                        .filter(c -> c.getName().toLowerCase().contains(term.toLowerCase()))
                        .collect(Collectors.toList());
            } else {
                courses = COURSES;
            }
            var page = new CoursesPage(courses, term);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = COURSES.stream()
                    .filter(c -> id.equals(c.getId()))
                    .findFirst()
                    .orElse(null);
            if (course == null) {
                throw new NotFoundResponse("User not found");
            }
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
           var page = new UsersPage(USERS);
           ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/build", ctx -> ctx.render("users/build.jte"));

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = USERS.stream()
                    .filter(u -> id.equals(u.getId()))
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }
            var page =new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.post("/users", ctx -> {
            var firstName = ctx.formParam("firstName").trim();
            var secondName = ctx.formParam("secondName").trim();
            var email = ctx.formParam("email").trim().toLowerCase();

            var user = new User(firstName, secondName, email);
            UsersRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        return app;
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
