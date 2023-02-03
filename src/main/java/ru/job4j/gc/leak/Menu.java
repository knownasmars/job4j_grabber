package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public static final int ADD_POST = 1;
    public static final int ADD_MANY_POST = 2;
    public static final int SHOW_ALL_POSTS = 3;
    public static final int DELETE_POST = 4;

    public static final String SELECT = "�������� ����";
    public static final String COUNT = "�������� ���������� ����������� ������";
    public static final String TEXT_OF_POST = "������� �����";
    public static final String EXIT = "����� ������";

    public static final String MENU = """
                ������� 1 ��� �������� �����.
                ������� 2, ����� ������� ������������ ���������� ������.
                ������� 3, ����� �������� ��� �����.
                ������� 4, ����� ������� ��� �����.
                ������� ����� ������ ����� ��� ������.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        String text, count;
        int intCount, userChoice;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                text = scanner.nextLine();
                System.out.println(COUNT);
                count = scanner.nextLine();
                intCount = Integer.parseInt(count);
                for (int i = 0; i < intCount; i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                postStore.getPosts()
                        .forEach(x ->
                                System.out.println(x
                                        .getId()
                                        .toString() + " ��� ������"
                                )
                        );
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}