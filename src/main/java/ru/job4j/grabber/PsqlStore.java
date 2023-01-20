package ru.job4j.grabber;

import java.io.IOException;;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private Connection cnn;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cnn = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("username"),
                cfg.getProperty("password")
        );
    }

    public static void main(String[] args) throws SQLException {
        Post post = new Post(
                "Вакансия1",
                "https://career.habr.com/",
                "Мы предлагаем лучшую работу в мире",
                LocalDateTime.parse("2023-01-21T01:30:00",
                        DateTimeFormatter.ISO_DATE_TIME)
        );
        Properties cfg = new Properties();
        try {
            cfg.load(PsqlStore.class.getClassLoader()
                    .getResourceAsStream("grabberapp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PsqlStore psql = new PsqlStore(cfg);
        psql.save(post);
        System.out.println(psql.getAll());
        System.out.println(psql.findById(3));

    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement(
                "insert into post(name, text, link, created) "
                        + "values(?, ?, ?, ?)")) {
            ps.setString(1, post.getTitle());
            ps.setString(3, post.getLink());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(
                "select * from post")) {
            ResultSet rsl = ps.executeQuery();
            while (rsl.next()) {
                posts.add(getResultSet(rsl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private Post getResultSet(ResultSet rsl) throws SQLException {
        return new Post(
                rsl.getInt("id"),
                rsl.getString("name"),
                rsl.getString("text"),
                rsl.getString("link"),
                rsl.getTimestamp("created").toLocalDateTime()
        );
    }

    @Override
    public Post findById(int id) {
        try (PreparedStatement ps = cnn.prepareStatement(
                "select * from post where id = ?")) {
            ps.setInt(1, id);
            ResultSet rsl = ps.executeQuery();
            if (rsl.next()) {
                return getResultSet(rsl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}