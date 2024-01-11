package org.example.hexlet.util;

public class NamedRoutes {
    public static String rootPath() { return "/"; }

    // Courses
    public static String coursesPath() {
        return "/courses";
    }

    public static String buildCoursesPath() { return "/courses/build"; }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String coursePath(Long id) { return coursePath(String.valueOf(id)); }

    public static String editCoursePath(String id) { return "/courses/" + id + "/edit"; }

    public static String editCoursePath(Long id) { return editCoursePath(String.valueOf(id)); }

    // Users
    public static String usersPath() {
        return "/users";
    }

    public static String buildUsersPath() {
        return "/users/build";
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String footerGithubPath() { return "https://github.com/SerKonstantin"; }

    // Sessions
    public static String registerPath() { return "/register"; }

    public static String loginPath() { return "/login"; }

    public static String logoutPath() { return "/logout"; }

}
