package org.example.collections.forms;

import org.example.collections.Difficulty;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Difficulty
 */
public class DifficultyForm implements StandardForm<Difficulty> {
    private final Console console;
    private final Scanner scanner;

    public DifficultyForm(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public Difficulty build() {
        Reader reader = new Reader(console, scanner);
        Difficulty difficulty;
        Difficulty[] difficulties = Difficulty.values();
        Scanner scanner;
        for (Difficulty value : difficulties) {
            console.println(value + " (" + value.ordinal() + ")");
        }

        console.print("Выберите уровень сложности(\"int\" или \"String\"): ");
        scanner = new Scanner(System.in);
        difficulty = reader.inputDifficulty(difficulties, scanner);

        return difficulty;
    }

}