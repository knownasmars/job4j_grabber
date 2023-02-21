package ru.job4j.solid.lsp;

import java.time.LocalDate;

public class Food {

    String name;

    LocalDate createDate;

    LocalDate expiryDate;

    float price;

    float discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, float price, float discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }
}
