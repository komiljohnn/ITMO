package org.example.collections.forms;

import org.example.collections.Difficulty;
import org.example.utility.Console;

import java.util.Scanner;

public class DifficultyForm implements StandardForm<Difficulty> {
    private final Console console;

    public DifficultyForm(Console console) {
        this.console = console;
    }

    @Override
    public Difficulty build() {
        Difficulty difficulty = null;
        Difficulty[] difficulties = Difficulty.values();
        Scanner scanner;
        for (Difficulty value : difficulties) {
            console.println(value + " (" + value.ordinal() + ")");
        }

        while (true) {
            console.print("Выберите уровень сложности(\"int\" или \"String\"): ");
            scanner = new Scanner(System.in);
            difficulty = Reader.inputDifficulty(difficulties, scanner);
            if (difficulty != null) {
                break;
            }
        }
        return difficulty;
    }

}