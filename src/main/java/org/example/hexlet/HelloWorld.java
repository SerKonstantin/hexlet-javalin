package org.example.hexlet;

import io.javalin.Javalin;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.pseudoDatabases.courses.CoursesRepository;
import org.example.hexlet.pseudoDatabases.users.UsersRepository;
import org.example.hexlet.util.NamedRoutes;

import java.util.Collections;

public class HelloWorld {
    public static Javalin getApp() {
        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        // Imitate existing DB
        CoursesRepository.populate(64);
        UsersRepository.populate(64);

        app.get(NamedRoutes.homepagePath(), ctx -> {
            var visited = Boolean.parseBoolean(ctx.cookie("visited"));
            String currentUser = ctx.sessionAttribute("currentUser");
            var page = new MainPage(visited, currentUser);
            ctx.render("index.jte", Collections.singletonMap("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursesPath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.editCoursePath("{id}"), CoursesController::edit);
        app.post(NamedRoutes.coursePath("{id}"), CoursesController::update);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUsersPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.buildSessionsPath(), SessionController::build);
        app.post(NamedRoutes.sessionsPath(), SessionController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionController::destroy);

        return app;
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
