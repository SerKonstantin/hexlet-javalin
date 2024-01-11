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
        CoursesRepository.populate(128);
        UsersRepository.populate(16);

        app.get(NamedRoutes.rootPath(), ctx -> {
            var visited = Boolean.parseBoolean(ctx.cookie("visited"));
            String currentUser = ctx.sessionAttribute("username");
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
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.get(NamedRoutes.registerPath(), SessionController::renderRegisterForm);
        app.post(NamedRoutes.registerPath(), SessionController::handleRegister);
        app.get(NamedRoutes.loginPath(), SessionController::renderLoginForm);
        app.post(NamedRoutes.loginPath(), SessionController::handleLogin);
        app.post(NamedRoutes.logoutPath(), SessionController::handleLogout);

        return app;
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(7070);
    }
}
