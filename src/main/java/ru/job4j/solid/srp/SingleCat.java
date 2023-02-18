package ru.job4j.solid.srp;

public final class SingleCat {
    private static SingleCat cat = null;

    public SingleCat() {
    }

    public static SingleCat getCat() {
        if (cat == null) {
            cat = new SingleCat();
        }
        return cat;
    }
}
