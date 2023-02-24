package ru.job4j.solid.lsp.parkingservice;

public class FreightCar extends AbstractCar {

    private final int weight;

    boolean ticketIsPaid;

    public FreightCar(int weight, String regNum, boolean ticketIsPaid) {
        super(regNum);
        if (weight < 2 || weight > 3) {
            throw new IllegalArgumentException(
                    "Грузовой автомобиль должен весить больше 1 и меньше 4");
        }
        this.weight = weight;
        this.ticketIsPaid = ticketIsPaid;
    }

    public boolean isPaid() {
        return ticketIsPaid;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Грузовой. Госномер: " + regNum
                + ". Вес: (" + weight
                + "). Статус оплаты парковки: "
                + ticketIsPaid + ".";
    }
}
