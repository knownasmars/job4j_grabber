package ru.job4j.solid.ocp.reports;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportFormatJsonTest {
    @Test
    void whenGenerateJsonReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportFormatJson(store, parser);
        String delimiter = ",";
        StringBuilder expected = new StringBuilder()
                .append("[").append("{")
                .append("\"name\":\"Ivan\"").append(delimiter)
                .append("\"hired\":").append("{")
                .append("\"year\":").append(now.get(Calendar.YEAR)).append(delimiter)
                .append("\"month\":").append(now.get(Calendar.MONTH)).append(delimiter)
                .append("\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH)).append(delimiter)
                .append("\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY)).append(delimiter)
                .append("\"minute\":").append(now.get(Calendar.MINUTE)).append(delimiter)
                .append("\"second\":").append(now.get(Calendar.SECOND)).append("}").append(delimiter)
                .append("\"fired\":").append("{")
                .append("\"year\":").append(now.get(Calendar.YEAR)).append(delimiter)
                .append("\"month\":").append(now.get(Calendar.MONTH)).append(delimiter)
                .append("\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH)).append(delimiter)
                .append("\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY)).append(delimiter)
                .append("\"minute\":").append(now.get(Calendar.MINUTE)).append(delimiter)
                .append("\"second\":").append(now.get(Calendar.SECOND)).append("}").append(delimiter)
                .append("\"salary\":").append(100.0)
                .append("}").append("]");
        assertThat(engine.generate(em -> true)).isEqualTo(expected.toString());
    }
}