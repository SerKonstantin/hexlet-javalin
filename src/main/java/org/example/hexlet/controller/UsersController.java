package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.pseudoDatabases.users.UsersRepository;

import java.util.Collections;

public class UsersController {
    public static void index(Context ctx) {
        var users = UsersRepository.getUsers();
        var page = new UsersPage(users);
        ctx.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();

            var user = UsersRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResponse("User not found"));

            var page =new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        } catch (ValidationException e) {
            ctx.status(404).result("Page not found");
        }
    }
}
