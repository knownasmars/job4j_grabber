package ru.job4j.solid.ocp;

public class Car {
    final String body = "iron";
    final String fuel = "gaz";

    public Car(String body) {
    }

    public void launch() {
        System.out.println("Br.... Br....");
    }

    public void ride() {
        System.out.println("Whrooom");
    }
}
