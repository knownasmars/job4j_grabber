package ru.job4j.grabber.utils;

import java.time.*;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class HabrCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse, ISO_DATE_TIME);
    }
}