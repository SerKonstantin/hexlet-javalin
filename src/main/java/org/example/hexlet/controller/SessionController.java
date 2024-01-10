package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.util.NamedRoutes;

public class SessionController {
    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) {
        var username = ctx.formParam("username");

        // TODO password check

        ctx.sessionAttribute("currentUser", username);
        ctx.redirect(NamedRoutes.homepagePath());
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.homepagePath());
    }
}
