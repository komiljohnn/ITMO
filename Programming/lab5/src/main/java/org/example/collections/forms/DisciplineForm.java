package org.example.collections.forms;

import org.example.collections.Discipline;
import org.example.collections.validators.NameValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

public class DisciplineForm implements StandardForm<Discipline> {
    private final Console console;

    public DisciplineForm(Console console) {
        this.console = console;
    }

    public Discipline build() {
        Scanner scanner;
        Discipline discipline = new Discipline();

        Validate<String> validate = new NameValidate();
        scanner = new Scanner(System.in);
        while (true) {
            console.print("Введите название дисциплины(\"String\" != \"\" и " + "\"String\" != \"null\"): ");
            String name = Reader.inputStringValue(validate, scanner);
            if (!name.isBlank()) {
                discipline.setName(name);
                break;
            }
        }

        while (true) {
            long maxHours = (4 * 30 + 2) * 24;
            console.print("Введите количество часов лекций(\"long\" <= " + maxHours + "): ");
            long res = Reader.inputLongValue(scanner, maxHours);
            if (res != 0) {
                discipline.setLectureHours(res);
                break;
            }
        }

        return discipline;
    }

}