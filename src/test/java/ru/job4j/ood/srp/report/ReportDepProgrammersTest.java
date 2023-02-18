package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.programmers.ReportDepProgrammers;
import ru.job4j.ood.srp.store.MemStore;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportDepProgrammersTest {
    @Test
    void whenGeneratedThenWriteOutToCSV() throws Exception {
        Path source = Paths.get("report.csv");
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now,100.0);
        store.add(worker);
        ReportDateTimeParser reportDateTimeParser = new ReportDateTimeParser();
        ReportDepProgrammers rdp = new ReportDepProgrammers(store, reportDateTimeParser);
        rdp.generate(emp -> true);
        String date = reportDateTimeParser.parse(now);
        String delimiter = ";";
        String expected = String.join(
                System.lineSeparator(),
                "Name;Hired;Fired;Salary;",
                "Ivan" + delimiter + date + delimiter + date + delimiter + "100.0" + delimiter
        ).concat(System.lineSeparator());
        assertThat(Files.readString(source)).isEqualTo(expected);
    }
}