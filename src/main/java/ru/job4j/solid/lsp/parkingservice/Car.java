package ru.job4j.solid.lsp.parkingservice;

public class Car extends AbstractCar {

    private final int weight;

    boolean ticketIsPaid;

    public Car(int weight, String regNum, boolean ticketIsPaid) {
        super(regNum);
        if (weight != 1) {
            throw new IllegalArgumentException(
                    "Обычный автомобиль должен весить 1 единицу");
        }
        this.weight = weight;
        this.ticketIsPaid = ticketIsPaid;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Легковой. Госномер: " + regNum
                + ". Вес: (" + weight
                + "). Статус оплаты парковки: "
                + ticketIsPaid + ".";
    }
}
