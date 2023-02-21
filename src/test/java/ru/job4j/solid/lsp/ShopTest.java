package ru.job4j.solid.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void whenProductIsExpiredFor50PercentThenAddedToShop() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isNotNull();
    }

    @Test
    void whenShopClassIsInitialized() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getStore()).isInstanceOf(Shop.class);
    }

    @Test
    void whenProductAddedToStoredList() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 3, 21),
                100,
                0
        );
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        Shop shop = (Shop) controlQuality.getStore();
        assertThat(shop.getStored()).hasSize(1);
    }

    @Test
    void whenProductIsExpiredMoreThan75PercentThenDiscountAdded() {
        Food apple = new Food(
                "Apple",
                LocalDate.of(2023, 1, 20),
                LocalDate.of(2023, 2, 28),
                100,
                50
        );
        float expected = 50.0f;
        ControlQuality controlQuality = new ControlQuality(apple);
        controlQuality.distribute();
        assertThat(controlQuality.getFood().price).isEqualTo(expected);
    }
}