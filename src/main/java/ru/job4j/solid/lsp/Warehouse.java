package ru.job4j.solid.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    private List<Food> stored = new ArrayList<>();

    public List<Food> getStored() {
        return stored;
    }

    @Override
    public void store(Food food) {
        stored.add(food);
        System.out.println("Товар хранится на складе");
    }
}
