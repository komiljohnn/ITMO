package org.example.collections.forms.nonUserMode;

import org.example.collections.Discipline;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.collections.validators.NameValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Discipline, в режиме скрипта
 */
public class DisciplineFormNonUserMode implements StandardForm<Discipline>, nonUserMode {
    private String[] args;
    private final Console console;
    private final Scanner scanner;

    public DisciplineFormNonUserMode(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public Discipline build() {
        Discipline discipline = new Discipline();
        Reader reader = new Reader(console, scanner);
        boolean create;
        String name = getName(reader);
        if (name == null || name.isBlank()) {
            name = null;
            create = false;
            console.printError("Не может быть пустым или равным null\n");
        } else {
            create = true;
            console.println("Название дисциплины: " + name + "\n");
        }
        if (name != null) {
            discipline.setName(name);
        }

        Long hours = getLectureHours(reader);
        if (hours != null) {
            discipline.setLectureHours(hours);
        } else {
            create = false;
        }

        if (!create) {
            discipline = null;
        }
        return discipline;
    }

    public String getName(Reader reader) {
        console.println("\nПроверка названии дисциплины...");
        Validate<String> validate = new NameValidate();
        Scanner scanner = new Scanner(args[0]);
        return reader.inputStringValue(validate, scanner);
    }

    public Long getLectureHours(Reader reader) {
        console.println("Проверка количество часов лекции...");
        Scanner scanner = new Scanner(args[1]);
        long maxHours = (4 * 30 + 2) * 24;
        Long res = null;
        if (args[1].isBlank()) {
            console.println("Поле не может быть пустым или null\n");
        } else {
            res = reader.inputLongValue(scanner, maxHours);
            if (res != null) {
                console.println("Количество часов лекции: " + res + "\n");
            } else {
                console.println("Поле не может быть пустым или null\n");
            }
        }
        return res;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}