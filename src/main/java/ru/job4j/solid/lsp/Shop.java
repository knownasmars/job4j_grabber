package ru.job4j.solid.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private List<Food> stored = new ArrayList<>();

    public List<Food> getStored() {
        return stored;
    }

    @Override
    public boolean store(Food food) {
        System.out.println("Товар выставлен на прилавок!");
        return stored.add(food);
    }
}
