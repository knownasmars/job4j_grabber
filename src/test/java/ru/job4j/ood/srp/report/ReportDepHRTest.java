package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.hr.ReportDepHR;
import ru.job4j.ood.srp.store.MemStore;

import static org.assertj.core.api.Assertions.*;

class ReportDepHRTest {
    @Test
    void whenGeneratedWithNoDatesAndSalarySortedByDesc() {
        MemStore store = new MemStore();
        Employee ivan = new Employee("Ivan", 100);
        Employee steve = new Employee("Steve", 200);
        Employee julia = new Employee("Julia", 300);
        store.add(ivan);
        store.add(steve);
        store.add(julia);
        ReportDepHR report = new ReportDepHR(store);
        report.generate(emp -> true);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(julia.getName()).append(" ")
                .append(julia.getSalary())
                .append(System.lineSeparator())
                .append(steve.getName()).append(" ")
                .append(steve.getSalary())
                .append(System.lineSeparator())
                .append(ivan.getName()).append(" ")
                .append(ivan.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(emp -> true)).isEqualTo(expected.toString());
    }
}