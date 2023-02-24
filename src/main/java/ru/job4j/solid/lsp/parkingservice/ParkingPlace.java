package ru.job4j.solid.lsp.parkingservice;

public class ParkingPlace {

    private Vehicle vehicle;

    private final PlaceNumbers slotNumber;

    private boolean empty = true;

    public ParkingPlace(PlaceNumbers slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean setVehicle(Vehicle vehicle) {
        boolean rsl = false;
        if (isEmpty()) {
            this.vehicle = vehicle;
            setEmpty(false);
            rsl = true;
        }
        return rsl;
    }

    public PlaceNumbers getSlotNumber() {
        return slotNumber;
    }

    @Override
    public String toString() {
        return "Парковочное место " + slotNumber + " : " + System.lineSeparator()
                + "1. Автомобиль " + vehicle + System.lineSeparator()
                + "2. Место свободно? " + empty;
    }
}