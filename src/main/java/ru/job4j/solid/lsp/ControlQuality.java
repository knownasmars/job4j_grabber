package ru.job4j.solid.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    private Food food;

    private Store store;

    public ControlQuality(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public Store getStore() {
        return store;
    }

    private double validate() {
        if (food.expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Товар просрочен");
        }
        double totalLeftTime = (double) (ChronoUnit.DAYS.between(food.createDate, food.expiryDate));
        double left = (double) ChronoUnit.DAYS.between(food.createDate, LocalDate.now());
        return (totalLeftTime - left) / totalLeftTime * 100;
    }

    public void distribute() {
        store = getStore(food);
        store.store(food);
    }

    private Store getStore(Food food) {
        if (validate() >= 75) {
            return new Warehouse();
        } else if (validate() >= 25 && validate() < 75) {
            return new Shop();
        } else if (validate() < 1) {
            return new Trash();
        } else {
            food.price -= food.discount;
            System.out.println("Скидка на товар!");
            return new Shop();
        }
    }
}
