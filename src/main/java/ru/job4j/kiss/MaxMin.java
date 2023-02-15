package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static <T> T finder(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
        return list.get(0);
    }

    public <T> T max(List<T> list, Comparator<T> comparator) {
        return finder(list, comparator.reversed());
    }

    public <T> T min(List<T> list, Comparator<T> comparator) {
        return finder(list, comparator);
    }
}