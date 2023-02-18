package ru.job4j.ood.srp.report.programmers;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportDepProgrammers implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportDepProgrammers(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Path path = Paths.get("report.csv");
        String delimiter = ";";
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName())
                    .append(delimiter)
                    .append(dateTimeParser.parse(employee.getHired()))
                    .append(delimiter)
                    .append(dateTimeParser.parse(employee.getFired()))
                    .append(delimiter)
                    .append(employee.getSalary())
                    .append(delimiter)
                    .append(System.lineSeparator());
        }
        try (BufferedWriter out =
                     new BufferedWriter(
                             new FileWriter(path.toFile()))) {
            out.write(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}