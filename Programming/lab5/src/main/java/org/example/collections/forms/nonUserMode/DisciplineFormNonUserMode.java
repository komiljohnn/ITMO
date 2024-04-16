package org.example.collections.forms.nonUserMode;

import org.example.collections.Discipline;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.collections.validators.NameValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

public class DisciplineFormNonUserMode implements StandardForm<Discipline>, nonUserMode {
    private String[] args;
    private final Console console;

    public DisciplineFormNonUserMode(Console console) {
        this.console = console;
    }

    @Override
    public Discipline build() {
        Discipline discipline = new Discipline();

        Validate<String> validate = new NameValidate();
        Scanner scanner = new Scanner(args[0]);
        while (true) {
            String name = Reader.inputStringValue(validate, scanner);
            if (!name.isBlank()) {
                discipline.setName(name);
                break;
            } else {
                console.print("Введите название дисциплины(\"String\" != \"\" и " + "\"String\" != \"null\"): ");
                scanner = new Scanner(System.in);
            }
        }

        scanner = new Scanner(args[1]);
        while (true) {
            long maxHours = (4 * 30 + 2) * 24;
            long res = Reader.inputLongValue(scanner, maxHours);
            if (res != 0) {
                discipline.setLectureHours(res);
                break;
            } else {
                console.print("Введите количество часов лекций(\"long\" <= " + maxHours + "): ");
                scanner = new Scanner(System.in);
            }
        }

        return discipline;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}