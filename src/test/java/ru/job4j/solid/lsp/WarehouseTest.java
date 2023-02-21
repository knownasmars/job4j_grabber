package ru.job4j.solid.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    void whenProductIsExpiredLessThan25Percent() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 2, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isNotNull();
    }

    @Test
    void whenWarehouseClassIsInitialized() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 2, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isInstanceOf(Warehouse.class);
    }

    @Test
    void whenProductAddedToStoredList() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 2, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        Warehouse warehouse = (Warehouse) controlQuality.getStore();
        assertThat(warehouse.getStored()).hasSize(1);
    }
}