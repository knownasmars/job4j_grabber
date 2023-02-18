package ru.job4j.ood.srp.report.hr;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class EmployeeDescBySalary implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}
