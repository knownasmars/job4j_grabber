package ru.job4j.solid.srp;

public interface GroceriesShop<T, V> {
    void sell();

    void collectCash(T cash);

    T exchange(V currency);
}
