package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.databases.BaseRepository;
import org.example.hexlet.databases.courses.CoursesRepository;
import org.example.hexlet.databases.users.UsersRepository;
import org.example.hexlet.util.NamedRoutes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Collections;
import java.util.stream.Collectors;

public class HelloWorld {
    public static Javalin getApp() throws IOException, SQLException {
        // Set up database
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:play_with_javalin;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);
        var url = HelloWorld.class.getClassLoader().getResource("schema.sql");
        var file = new File(url.getFile());
        var sql = Files.lines(file.toPath())
                .collect(Collectors.joining("\n"));

        try (var connection = dataSource.getConnection(); var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

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

    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(7070);
    }
}
