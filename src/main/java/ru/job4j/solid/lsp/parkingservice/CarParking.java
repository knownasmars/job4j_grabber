package ru.job4j.solid.lsp.parkingservice;

import java.util.Optional;

public final class CarParking extends AbstractParking {
    public CarParking() {
        for (PlaceNumbers number : PlaceNumbers.values()) {
            getSlots().add(new ParkingPlace(number));
        }
    }

    @Override
    public boolean delete(Vehicle vehicle) {
        boolean rsl;
        if (vehicle == null) {
            throw new IllegalArgumentException("Car not found. Vehicle is null");
        }
        Optional<Boolean> opt = getSlots().stream()
                .filter(x -> x.getVehicle().equals(vehicle))
                .findFirst()
                .map(x -> {
                    x.setEmpty(true);
                    return x.setVehicle(null);
                });
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("The parking is full. Try another parking");
        }
        rsl = opt.get();
        return rsl;
    }
}
