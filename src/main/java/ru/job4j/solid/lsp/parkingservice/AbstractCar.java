package ru.job4j.solid.lsp.parkingservice;

public abstract class AbstractCar implements Vehicle {
    String regNum;

    public AbstractCar(String regNum) {
        this.regNum = regNum;
    }

    public String getRegNum() {
        return regNum;
    }
}
