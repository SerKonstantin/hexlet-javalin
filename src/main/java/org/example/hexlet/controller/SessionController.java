package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.sessions.LoginPage;
import org.example.hexlet.dto.sessions.RegisterPage;
import org.example.hexlet.model.User;
import org.example.hexlet.pseudoDatabases.users.UsersRepository;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.util.Security;

import java.util.Collections;

public class SessionController {
    public static void renderRegisterForm(Context ctx) {
        var page = new RegisterPage();
        ctx.render("sessions/register.jte", Collections.singletonMap("page", page));
    }

    public static void handleRegister(Context ctx) {
        var firstName = ctx.formParam("firstName").trim();
        var secondName = ctx.formParam("secondName").trim();
        var email = ctx.formParam("email").trim().toLowerCase();

        try {
            var nickname = ctx.formParamAsClass("nickname", String.class)
                    .check(value -> value.trim().length() >= 3, "Nickname should be at least 3 characters long")
                    .check(value -> UsersRepository.findByNickname(value).isEmpty(), "Nickname is already in use")
                    .check(value -> !value.equals("admin"), "Invalid nickname")
                    .get().trim();

            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Password doesn't match")
                    .check(value -> value.length() >= 6, "Please make password at least 6 characters long")
                    .get();

            var encryptedPassword = Security.encrypt(password);
            var user = new User(nickname, firstName, secondName, email, encryptedPassword);
            UsersRepository.save(user);
            ctx.sessionAttribute("username", nickname);
            ctx.redirect(NamedRoutes.rootPath());

        } catch (ValidationException e) {
            var nickname = ctx.formParam("nickname");
            var page = new RegisterPage(nickname, firstName, secondName, email, e.getErrors());
            ctx.render("sessions/register.jte", Collections.singletonMap("page", page)).status(422);
        }
    }

    public static void renderLoginForm(Context ctx) {
        var page = new LoginPage();
        ctx.render("sessions/login.jte", Collections.singletonMap("page", page));
    }

    public static void handleLogin(Context ctx) {
        var nickname = ctx.formParam("nickname");
        var password = Security.encrypt(ctx.formParam("password"));

        // Should be links to data instead of pure strings
        if (nickname.equals("admin") && password.equals(Security.encrypt("admin"))) {
            ctx.sessionAttribute("username", "admin");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        if (UsersRepository.findByNickname(nickname).isEmpty()) {
            var errorMessage = "Username or password is incorrect";
            var page = new LoginPage(nickname, errorMessage);
            ctx.render("sessions/login.jte", Collections.singletonMap("page", page));
            return;
        }

        var user = UsersRepository.findByNickname(nickname).get();
        if (password.equals(user.getPassword())) {
            ctx.sessionAttribute("username", user.getNickname());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var errorMessage = "Username or password is incorrect";
            var page = new LoginPage(nickname, errorMessage);
            ctx.render("sessions/login.jte", Collections.singletonMap("page", page));
        }
    }

    public static void handleLogout(Context ctx) {
        ctx.sessionAttribute("username", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
}
