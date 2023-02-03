package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private Map<Integer, Post> posts = new HashMap<>();

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public void add(Post post) {
        int id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return new PostStore().posts.values();
    }
}