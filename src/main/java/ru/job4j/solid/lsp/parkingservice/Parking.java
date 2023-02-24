package ru.job4j.solid.lsp.parkingservice;

import java.util.List;

public interface Parking {
    boolean add(Vehicle vehicle);

    List<ParkingPlace> getSlots();
}