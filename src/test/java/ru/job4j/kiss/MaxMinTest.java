package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    private List<Integer> list = new ArrayList<>();

    @BeforeEach
    void init() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void whenFindMax() {
        MaxMin mm = new MaxMin();
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int max = mm.max(list, comparator);
        assertThat(max).isEqualTo(3);
    }

    @Test
    void whenFindMin() {
        MaxMin mm = new MaxMin();
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int min = mm.min(list, comparator);
        assertThat(min).isEqualTo(1);
    }
}