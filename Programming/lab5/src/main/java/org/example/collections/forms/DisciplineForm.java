package org.example.collections.forms;

import org.example.collections.Discipline;
import org.example.collections.validators.NameValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Discipline
 */
public class DisciplineForm implements StandardForm<Discipline> {
    private final Console console;
    private final Scanner scanner;

    public DisciplineForm(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    public Discipline build() {
        Discipline discipline = new Discipline();
        Reader reader = new Reader(console, scanner);

        getName(discipline, reader);

        getLectureHours(discipline, reader);

        return discipline;
    }

    public void getName(Discipline discipline, Reader reader) {
        Validate<String> validate = new NameValidate();
        while (true) {
            console.print("Введите название дисциплины(\"String\" != \"\" и " + "\"String\" != \"null\"): ");
            String name = reader.inputStringValue(validate, scanner);
            if (name != null && !name.isBlank()) {
                discipline.setName(name);
                break;
            } else {
                console.printError("Нельзя вводить пустое значение");
            }
        }
    }

    public void getLectureHours(Discipline discipline, Reader reader) {
        while (true) {
            try {
                long maxHours = (4 * 30 + 2) * 24;
                console.print("Введите количество часов лекций(\"long\" <= " + maxHours + "): ");
                Long res = reader.inputLongValue(scanner, maxHours);
                if (res != null) {
                    discipline.setLectureHours(res);
                    break;
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }
    }

}