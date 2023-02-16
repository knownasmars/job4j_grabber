package ru.job4j.solid.srp;

public interface ElectronicCard {
    int read(int data);

    void pay();

    void write();

    void collect(int data);
}
