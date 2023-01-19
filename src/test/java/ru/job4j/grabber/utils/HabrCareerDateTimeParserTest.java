package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {
    @Test
    void whenParseDateThenOk() {
        String parsed = "2011-12-03T10:15:30+01:00";
        HabrCareerDateTimeParser h = new HabrCareerDateTimeParser();
        LocalDateTime date = h.parse(parsed);
        assertThat(date).isEqualTo("2011-12-03T10:15:30");
    }

    @Test
    void whenParseIncorrectPattern() {
        assertThatException().isThrownBy(() -> {
            String parsed = "2011/12/03";
            HabrCareerDateTimeParser h = new HabrCareerDateTimeParser();
            LocalDateTime date = h.parse(parsed);
        }).isInstanceOf(DateTimeParseException.class);
    }

    @Test
    void whenParsedNullThenNull() {
        assertThatException().isThrownBy(() -> {
            String parsed = "";
            HabrCareerDateTimeParser h = new HabrCareerDateTimeParser();
            LocalDateTime date = h.parse(parsed);
        }).isInstanceOf(DateTimeParseException.class);
    }
}