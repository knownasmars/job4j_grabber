package ru.job4j.solid.ocp;

import java.util.function.BiFunction;

public class CurrencyConverter {

    private static class SimpleConverter {

        public double convert(double a, double b) {
            return a * b;
        }

        private static class AbstractConverter<T> {
            public T convert(BiFunction<T, T, T> function, T first, T second) {
                return function.apply(first, second);
            }
        }
    }
}
