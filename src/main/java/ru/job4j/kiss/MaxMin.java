package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public static <T> T finder(List<T> list, BiPredicate<T, T> predicate) {
        T rsl = list.get(0);
        for (T value : list) {
            if (predicate.test(value, rsl)) {
                rsl = value;
            }
        }
        return rsl;
    }

    public <T> T max(List<T> list, Comparator<T> comparator) {
        return finder(list, (x, y) -> comparator.compare(x, y) > 0);
    }

    public <T> T min(List<T> list, Comparator<T> comparator) {
        return finder(list, (x, y) -> comparator.compare(x, y) < 0);
    }
}