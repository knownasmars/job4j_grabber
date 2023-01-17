package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    private Properties load() {
        Properties cfg = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream(
                "rabbit.properties")) {
            cfg.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return cfg;
    }

    public static void main(String[] args) {
        AlertRabbit ar = new AlertRabbit();
        Properties properties = ar.load();
        int timeValue = Integer.parseInt(
                properties.getProperty(
                        "rabbit.interval")
        );
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(timeValue)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
        }
    }
}