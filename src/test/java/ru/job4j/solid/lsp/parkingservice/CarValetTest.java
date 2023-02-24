package ru.job4j.solid.lsp.parkingservice;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class CarValetTest {
    @Test
    void whenParkCarThenReturnTrue() {
        Parking parking =  new CarParking();
        Vehicle vehicle = new Car(1, "77AFDF512", true);
        CarValet carValet = new CarValet(vehicle, parking);
        assertThat(carValet.park()).isTrue();
    }

    @Test
    void whenParking10Cars() {
        Parking parking =  new CarParking();
        CarValet carValet = new CarValet();
        carValet.setParking(parking);
        Stream.of(new Car(1,"1", true),
                new Car(1, "2", true),
                new Car(1, "3", true),
                new Car(1, "4", true),
                new Car(1, "5", true),
                new Car(1, "6", true),
                new Car(1, "7", true),
                new Car(1, "8", true),
                new Car(1, "9", true),
                new Car(1, "10", true)
        ).forEach((x) -> {
            carValet.setVehicle(x);
            assertThat(carValet.park()).isTrue();
        });
    }

    @Test
    void whenParkingIsFull() {
        Parking parking =  new CarParking();
        CarValet carValet = new CarValet();
        carValet.setParking(parking);
        Stream.of(new Car(1,"1", true),
                new Car(1, "2", true),
                new Car(1, "3", true),
                new Car(1, "4", true),
                new Car(1, "5", true),
                new Car(1, "6", true),
                new Car(1, "7", true),
                new Car(1, "8", true),
                new Car(1, "9", true),
                new Car(1, "10", true)
        ).forEach((x) -> {
            carValet.setVehicle(x);
            carValet.park();
        });
        Vehicle eleventhVehicle = new Car(1, "11", true);
        carValet.setVehicle(eleventhVehicle);
        assertThatThrownBy(carValet::park)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenParkFreightCarThenUse3Slots() {
        Parking parking =  new CarParking();
        Vehicle vehicle = new FreightCar(3, "77AFDF512", true);
        CarValet carValet = new CarValet(vehicle, parking);
        carValet.park();
        assertThat(parking.getSlots().stream()
                .filter(x -> x.getSlotNumber().equals(PlaceNumbers.A1))
                        .findFirst()
                        .get()
                        .getVehicle()
                        .getWeight()).isEqualTo(3);
    }

    @Test
    void whenParkNormalCarAfterFreightThenGetAnotherParkingPlace() {
        Parking parking =  new CarParking();
        Vehicle car = new Car(1, "33AS242", true);
        Vehicle freightCar = new FreightCar(3, "77AFDF512", true);
        CarValet carValet = new CarValet(car, parking);
        carValet.park();
        carValet.setVehicle(freightCar);
        carValet.park();
        assertThat(parking.getSlots().stream()
                .filter(x -> !x.isEmpty())
                .skip(1)
                .findFirst()
                .get()
                .getVehicle()
                .getWeight()).isEqualTo(3);
    }

    @Test
    void when8SlotsAreBusyAndParkFreightCarWithWeight3ThenException() {
        Parking parking =  new CarParking();
        CarValet carValet = new CarValet();
        carValet.setParking(parking);
        Stream.of(new Car(1,"1", true),
                new Car(1, "2", true),
                new Car(1, "3", true),
                new Car(1, "4", true),
                new Car(1, "5", true),
                new Car(1, "6", true),
                new Car(1, "7", true),
                new Car(1, "8", true)
        ).forEach((x) -> {
            carValet.setVehicle(x);
            carValet.park();
        });
        Vehicle vehicle = new FreightCar(3, "9", true);
        carValet.setVehicle(vehicle);
        assertThatThrownBy(carValet::park)
                .isInstanceOf(IllegalArgumentException.class);
    }
}