package org.example.collections.forms.nonUserMode;

import org.example.collections.Difficulty;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.utility.Console;

import java.util.Scanner;

public class DifficultyFormNonUserMode implements StandardForm<Difficulty>, nonUserMode {
    private String[] args;
    private final Console console;

    public DifficultyFormNonUserMode(Console console) {
        this.console = console;
    }

    @Override
    public Difficulty build() {
        Difficulty difficulty;
        Difficulty[] difficulties = Difficulty.values();
        Scanner scanner;

        scanner = new Scanner(args[0]);
        while (true) {

            difficulty = Reader.inputDifficulty(difficulties, scanner);
            if (difficulty != null) {
                break;
            } else {
                for (Difficulty value : difficulties) {
                    console.print(value + " (" + value.ordinal() + ")");
                }
                console.print("Выберите уровень сложности(\"int\" или \"String\"): ");
                scanner = new Scanner(System.in);
            }
        }
        return difficulty;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}