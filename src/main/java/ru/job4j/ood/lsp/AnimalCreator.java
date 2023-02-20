package ru.job4j.ood.lsp;

class Animal {
    protected int paws;
    protected int power;
    protected float health;

    public Animal(int paws, int power, float health) {
        this.paws = paws;
        this.power = power;
        this.health = health;
    }

    public void roar() {
        if (power < 3) {
            throw new IllegalArgumentException("Need more power!");
        }
        System.out.println("Rooooar, i'm animal!");
    }

    public void fight() {
        health = (float) (health * 0.5);
    }

    public float getHealth() {
        health = visitDoctor(health);
        if (health != 100.0) {
            throw new IllegalArgumentException("Animal needs to visit a VetDoctor");
        }
        System.out.println("My health is " + health);
        return health;
    }

    public static float visitDoctor(float health) {
        return health * 2;
    }
}

class Panther extends Animal {
    public Panther(int paws, int power, float health) {
        super(paws, power, health);
    }

    /**
     * В данноме методе 1-ое нарушение LSP.
     * Preconditions в классе наследнике не должны быть усилены.
     */
    public void roar() {
        if (power < 5) { /* <= предусловие */
            throw new IllegalArgumentException("Need more power!");
        }
        System.out.println("Roooooar! I'm a panther");
    }

    /**
     * В данноме методе 2-ое нарушение LSP.
     * Postconditions в классе наследнике не должны ослаблены.
     * Должны бать проверка как и в базовом классе
     */
    public float getHealth() {
        health = visitDoctor(health);
        System.out.println("My health is " + health);
        return health;
    }

    public void fight() {
        System.out.println("I fought!");
        health = (float) (health * 0.5);
    }
}

class Goat extends Animal {
    public Goat(int paws, int power, float health) {
        super(paws, power, health);
    }

    public void roar() {
    }

    public float getHealth() {
        health = visitDoctor(health);
        System.out.println("My health is " + health);
        return health;
    }
}

class Zoo {
    protected Animal animal;

    public Zoo(Animal animal) {
        validate(animal);
        this.animal = animal;
    }

    protected void validate(Animal animal) {
        if (animal.power < 5) {
            System.out.println("It's kind animal");
        }
        if (animal.power > 5) {
            throw new IllegalArgumentException("Dangerous animal!");
        }
    }
}

class PetShop extends Zoo {
    protected Animal animal;

    /**
     * В данноме методе 3-е нарушение LSP.
     * Invariants (условия) в классе наследнике должны быть также соблюдены.
     * Нарушается проверка условий хищное животное или нет.
     */
    public PetShop(Animal animal) {
        super(animal);
    }
}

public class AnimalCreator {
    public static void main(String[] args) {
        Animal panther = new Panther(4, 5, 100);
        panther.roar();
        panther.fight();
        System.out.println(panther.health);
        panther.fight();
        System.out.println(panther.health);
        panther.getHealth();
        Animal goat = new Goat(0, 0, 100);
        goat.fight();
        Zoo zoo = new Zoo(goat);
        PetShop petShop = new PetShop(panther);
    }
}