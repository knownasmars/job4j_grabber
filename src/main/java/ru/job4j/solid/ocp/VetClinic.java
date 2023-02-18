package ru.job4j.solid.ocp;

import java.util.List;

public class VetClinic {
    private static class Dog {

        public void toBeHealed() {
            String say = "Gav, Gav!! Thank you Doctor";
            System.out.println(say);
        }

        public static void main(String[] args) {
            List<Dog> animals = List.of(new Dog());
            animals.forEach(Dog::toBeHealed);
        }
    }
}
