package ru.job4j.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    public void getLoad(String path) {
        load(path);
    }

    @Override
    protected String load(String key) {
        Path path = Paths.get(key);
        String pathString = path + "\\" + cachingDir;
        String rsl = null;
        try (BufferedReader bis = new BufferedReader(
                new FileReader(pathString))) {
            StringBuilder sb = new StringBuilder();
            bis.lines().forEach(sb::append);
            rsl = sb.toString();
            SoftReference<String> soft = new SoftReference<>(rsl);
            cache.put(key, soft);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}