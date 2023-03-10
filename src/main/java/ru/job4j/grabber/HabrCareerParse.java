package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com/";

    private final DateTimeParser dateTimeParser;

    private static final int PAGES = 5;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) {
        String text;
        try {
            text = Jsoup.connect(link).get()
                    .select(".style-ugc")
                    .text();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return text;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= PAGES; i++) {
            try {
                Connection connection = Jsoup.connect(String.format("%s?page=%s", link, i));
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> {
                    posts.add(postParse(row));
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    private Post postParse(Element row) {
        Element first = row.select(".vacancy-card__title").first();
        Element linkElement = first.child(0);
        String linkToVacancy = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        Post post = new Post(
                first.text(),
                linkToVacancy,
                retrieveDescription(
                        String.format("%s%s", SOURCE_LINK,
                                linkToVacancy)),
                dateTimeParser.parse(
                        row.select(".vacancy-card__date").
                                first().child(0).attr("datetime"))
        );
        return post;
    }
}