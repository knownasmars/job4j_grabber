package ru.job4j.solid.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void whenProductIsExpiredThenGotToTrash() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 2, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isNotNull();
    }

    @Test
    void whenTrashClassIsInitialized() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 2, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isInstanceOf(Trash.class);
    }

    @Test
    void whenProductAddedToStoredList() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 2, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        Trash trash = (Trash) controlQuality.getStore();
        assertThat(trash.getStored()).hasSize(1);
    }
}