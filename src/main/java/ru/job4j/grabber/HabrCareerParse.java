package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        int i = 1;
        while (i <= 5) {
            Connection connection = Jsoup.connect(String.format("%s?page=%s", PAGE_LINK, i));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
            HabrCareerParse descPeser = new HabrCareerParse();
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                Element dateElement = row.select(".vacancy-card__date").first();
                Element dateElementChild = dateElement.child(0);
                String vacancyDate = dateElementChild.attr("datetime");
                LocalDateTime dateFormatted = parser.parse(vacancyDate);
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));

                System.out.printf("%s %s %s%n", dateFormatted, vacancyName, link);
                try {
                    System.out.println(
                            descPeser.retrieveDescription(link));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            i++;
        }
    }

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        String desc = "";
        Elements titleRow = document.select(
                ".section-title__title");
        String title = titleRow.first().text();
        Elements descRow = document.select(
                ".vacancy-description__text");
        String text = descRow.text();
        desc = title
                + System.lineSeparator()
                + text;
        return desc;
    }
}
