package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.pseudoDatabases.users.UsersRepository;
import org.example.hexlet.util.NamedRoutes;

import java.util.Collections;

public class UsersController {
    public static void index(Context ctx) {
        var users = UsersRepository.getUsers();
        var page = new UsersPage(users);
        ctx.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();

            var user = UsersRepository.findById(id);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }

            var page =new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        } catch (ValidationException e) {
            ctx.status(404).result("Page not found");
        }
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName").trim();
        var secondName = ctx.formParam("secondName").trim();
        var email = ctx.formParam("email").trim().toLowerCase();

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Password doesn't match")
                    .check(value -> value.length() >= 6, "Please make password at least 6 characters long")
                    .get();
            var user = new User(firstName, secondName, email, password);
            UsersRepository.save(user);
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(firstName, secondName, email, e.getErrors());
            ctx.render("users/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }
}
