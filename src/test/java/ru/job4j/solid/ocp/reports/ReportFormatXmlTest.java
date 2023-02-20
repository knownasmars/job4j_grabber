package ru.job4j.solid.ocp.reports;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.XmlReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportFormatXmlTest {
    @Test
    void whenGenerateXmlReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.0);
        DateTimeParser<Calendar> parser = new XmlReportDateTimeParser();
        store.add(worker);
        String date = parser.parse(now);
        Report engine = new ReportFormatXml(store);
        String exp = String.format("""
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <fired>%s</fired>
                        <hired>%s</hired>
                        <name>Ivan</name>
                        <salary>100.0</salary>
                    </employee>
                </employees>
                """, date, date);
        assertThat(engine.generate(em -> true)).isEqualTo(exp);
    }
}