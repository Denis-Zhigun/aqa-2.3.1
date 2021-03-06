package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String setDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String setName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String setPhone() {
        return faker.numerify("+79#########");
    }

    public static String getNewCity() {
        return faker.address().city();
    }
}
