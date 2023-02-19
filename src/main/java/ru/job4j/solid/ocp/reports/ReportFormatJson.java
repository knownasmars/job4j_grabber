package ru.job4j.solid.ocp.reports;

import com.google.gson.Gson;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportFormatJson implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public ReportFormatJson(Store store, DateTimeParser<Calendar> dateTimeParser, Gson gson) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}
