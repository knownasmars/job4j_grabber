package ru.job4j.solid.lsp;

import java.util.List;

public interface Store {
    List<Food> getStored();

    void store(Food food);
}
