package ru.job4j.grabber.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class HabrCareerDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(parse, dateTimeFormatter);
        return localDateTime;
    }
}