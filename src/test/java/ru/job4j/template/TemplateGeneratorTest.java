package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class TemplateGeneratorTest {
    @Test
    void whenInvokeProduceThenGetTrue() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Mars");
        map.put("subject", "you");
        assertThat(generator.produce(template, map)).isEqualTo("I am a Mars, Who are you?");
    }

    @Test
    void whenSearchedKeysAreNotFoundThenGetException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Mars");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenExtraKeysFoundInMapThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Mars");
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }
}