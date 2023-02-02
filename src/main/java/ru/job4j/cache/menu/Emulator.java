package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    private static String path = ".\\src\\main\\java\\ru\\job4j\\cache\\files\\";

    private static final String CASHING_DIRECTORY = "1";
    private static final String LOAD_CASH = "2";
    private static final String READ_CASH = "3";

    private static final String INPUT_FILENAME = "Введите название директории:";
    private static final String DIRECTORY_CASHED = "Директория получена. Загрузите содержимое файла";

    private static String filaName;

    public static final String MENU = """
                ------------------------------------------
                  >>>>>>>> Выберите пункт меню <<<<<<<<<
                ------------------------------------------
                1 - Ввести название кэшируемого файла
                2 - Загрузить содержимое файла в кэш
                3 - Получить содержимое файла из кэша
                Чтобы выйти введите любой другой текст
                ------------------------------------------
            """;

    public static void main(String[] args) {
        boolean run = true;
        String answer;
        String userChoice;
        DirFileCache dirFileCache = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (run) {
                System.out.println(MENU);
                userChoice = br.readLine();
                if (CASHING_DIRECTORY.equals(userChoice)) {
                    System.out.println("Пользователь выбрал: " + CASHING_DIRECTORY);
                    System.out.println(INPUT_FILENAME);
                    filaName = br.readLine();
                    validate(filaName);
                    dirFileCache = new DirFileCache(filaName);
                    System.out.println(DIRECTORY_CASHED);
                } else if (LOAD_CASH.equals(userChoice)) {
                    System.out.println("Пользователь выбрал: " + LOAD_CASH);
                    if (dirFileCache == null) {
                        System.out.println(
                                "Сначала введите название файла (пункт 1)." + System.lineSeparator()
                                        + "Для продолжения введите \"0\", для выхода - любой другой ответ."
                        );
                        answer = br.readLine();
                        if (!"0".equals(answer)) {
                            break;
                        }
                        continue;
                    }
                    dirFileCache.getLoad(path);
                } else if (READ_CASH.equals(userChoice)) {
                    System.out.println("Пользователь выбрал: " + READ_CASH);
                    if (dirFileCache == null) {
                        System.out.println(
                                "Кэш пуст.Сначала необходимо загрузить кэш." + System.lineSeparator()
                                + "Для этого: " + System.lineSeparator()
                                + "1. Выберите пункт 1 и введите название файла." + System.lineSeparator()
                                + "2. Выберите пункт 2 и загрузите кэш." + System.lineSeparator()
                                + "Для продолжения введите \"0\", для выхода - любой другой ответ."
                        );
                        answer = br.readLine();
                        if (!"0".equals(answer)) {
                            break;
                        }
                        continue;
                    }
                    Object object = dirFileCache.get(path);
                    System.out.println(object);
                } else {
                    run = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String input) {
        if (!filaName.endsWith(".txt")) {
            throw new IllegalArgumentException(
                    "Path has to be ended with \".txt\""
            );
        }
    }
}
