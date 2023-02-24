package ru.job4j.solid.lsp.parkingservice;

import java.util.Iterator;
import java.util.Optional;

public class CarValet {
    Vehicle vehicle;

    Parking parking;

    public CarValet() {
    }
    public CarValet(Vehicle vehicle, Parking parking) {
        this.vehicle = vehicle;
        this.parking = parking;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public boolean park() {
        boolean rsl;
        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Car valet couldn't find a car. Vehicle is null"
            );
        }
        if (parking == null) {
            throw new IllegalArgumentException(
                    "Choose tha parking where to park. Parking is null"
            );
        }
        if (vehicle.getWeight() == 1) {
            rsl = add((Car) vehicle);
        } else {
            rsl = add((FreightCar) vehicle);
        }
        return rsl;
    }

    private boolean add(Car car) {
        Optional<Boolean> opt = parking.getSlots().stream()
                .filter(ParkingPlace::isEmpty)
                .findFirst()
                .map(x -> {
                    x.setEmpty(false);
                    return x.setVehicle(vehicle);
                });
        if (opt.isEmpty()) {
            throw new IllegalArgumentException(
                    "The parking is full. There's no place to park your car type");
        }
        return true;
    }

    private boolean add(FreightCar freightCar) {
        parking.getSlots().stream()
                .skip(getPlaceNumber(freightCar.getWeight()))
                .limit(freightCar.getWeight())
                .forEach(x -> {
                    x.setVehicle(vehicle);
                    x.setEmpty(false);
                });
        return true;
    }

    public int getPlaceNumber(int weight) {
        if (weight < 1 || weight > 3) {
            throw new IllegalArgumentException("Input correct car weight");
        }
        int count = 0, place = 0;
        boolean rsl;
        Iterator<ParkingPlace> iterator = parking.getSlots().iterator();
        ParkingPlace next = iterator.next();
        PlaceNumbers placeNumber = next.getSlotNumber();
        while (iterator.hasNext() && count != weight) {
            next = iterator.next();
            rsl = next.isEmpty();
            if (rsl) {
                count++;
            }
            if (!rsl) {
                place++;
                count = 0;
                placeNumber = next.getSlotNumber();
            }
        }
        if (count != weight) {
            throw new IllegalArgumentException(
                    "The parking is full. There's no place to park your car type");
        }
        return place;
    }
}
