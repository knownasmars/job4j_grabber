package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.accountancy.ReportDepAccountancy;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportDepAccountancyTest {
    @Test
    void whenGeneratedWithConversionFromRUBToUSD() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        double conversionRate = 0.013;
        InMemoryCurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        ReportDepAccountancy report =
                new ReportDepAccountancy(
                        store, parser, currencyConverter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary() * conversionRate)
                .append(System.lineSeparator());
        assertThat(report.generate(emp -> true)).isEqualTo(expected.toString());
    }
}