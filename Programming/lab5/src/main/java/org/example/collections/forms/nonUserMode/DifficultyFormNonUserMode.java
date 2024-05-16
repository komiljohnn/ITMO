package org.example.collections.forms.nonUserMode;

import org.example.collections.Difficulty;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Difficulty, в режиме скрипта
 */
public class DifficultyFormNonUserMode implements StandardForm<Difficulty>, nonUserMode {
    private String[] args;
    private final Console console;
    private final Scanner scanner;
    private boolean create;

    public DifficultyFormNonUserMode(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public Difficulty build() {
        Reader reader = new Reader(console, scanner);
        console.println("Проверка сложности...");
        Difficulty difficulty;
        Difficulty[] difficulties = Difficulty.values();
        Scanner scanner;

        scanner = new Scanner(args[0]);
        if (args[0].isBlank()) {
            difficulty = null;
            create = true;
        } else {
            Difficulty tmp = reader.inputDifficulty(difficulties, scanner);
            if (tmp == null) {
                create = false;
                difficulty = null;
            } else {
                difficulty = tmp;
            }
        }
        console.println("Сложность: " + args[0]);
        return difficulty;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public boolean isCreate() {
        return create;
    }

}