package org.example.hexlet.util;

import net.datafaker.Faker;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Random RANDOM = new Random(123);

    public static List<Course> getCourses(int count) {
        List<Course> courses = new ArrayList<>();
        Faker faker = new Faker(new Locale("en"), RANDOM);

        for (var i = 0; i < count; i++) {
            var name = faker.educator().course();
            var description = faker.lorem().paragraph();
            var course = new Course(name, description);
            courses.add(course);
        }

        return courses;
    }

    public static List<User> getUsers(int count) {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker(new Locale("en"), RANDOM);

        for (var i = 0; i < count; i++) {
            var firstName = faker.name().firstName();
            var lastName = faker.name().lastName();
            var email = faker.internet().emailAddress();
            var password = faker.internet().password(6, 8);
            var user = new User(firstName, lastName, email, password);
            users.add(user);
        }

        return users;
    }
}
