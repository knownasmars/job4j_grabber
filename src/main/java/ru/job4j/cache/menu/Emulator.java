package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    private static String path = ".\\src\\main\\java\\ru\\job4j\\cache\\files\\";

    private static final String CASHING_DIRECTORY = "1";
    private static final String READ_CASH = "2";
    private static final String INPUT_FILENAME = "Введите название директории:";
    private static final String DIRECTORY_CASHED = "Директория получена. Загрузите содержимое файла";
    private static final String USER_SELECTED = "Пользователь выбрал: ";
    private static final String ENTER_OR_EXIT = "Для продолжения введите \"0\", для выхода - любой другой ответ.";

    private static String fileName;
    public static final String MENU = """
                ------------------------------------------
                  >>>>>>>> Выберите пункт меню <<<<<<<<<
                ------------------------------------------
                1 - Ввести название кэшируемого файла
                2 - Получить содержимое файла из кэша
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
                    System.out.println(USER_SELECTED + CASHING_DIRECTORY);
                    System.out.println(INPUT_FILENAME);
                    fileName = br.readLine();
                    validate();
                    dirFileCache = new DirFileCache(fileName);
                    System.out.println(DIRECTORY_CASHED);
                } else if (READ_CASH.equals(userChoice)) {
                    System.out.println(USER_SELECTED + READ_CASH);
                    if (dirFileCache == null) {
                        System.out.println(
                                "Сначала введите название файла (пункт 1)." + System.lineSeparator()
                                        + ENTER_OR_EXIT
                        );
                        answer = br.readLine();
                        if (!"0".equals(answer)) {
                            break;
                        }
                        continue;
                    }
                    Object obj = dirFileCache.get(path);
                    System.out.println(obj);
                } else {
                    run = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate() {
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException(
                    "Path has to be ended with \".txt\""
            );
        }
    }
}
