package ru.job4j.solid.lsp.parkingservice;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking implements Parking {
    private final List<ParkingPlace> slots
            = new ArrayList<>(PlaceNumbers.values().length);

    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<ParkingPlace> getSlots() {
        return slots;
    }

    public abstract boolean delete(Vehicle vehicle);
}
