package org.example.collections.forms.nonUserMode;

import org.example.collections.Coordinates;
import org.example.collections.LabWork;
import org.example.collections.forms.*;
import org.example.collections.validators.*;
import org.example.managers.IdManager;
import org.example.utility.Console;

import java.time.LocalDate;
import java.util.Scanner;

public class LabWorkFormNonUserMode implements StandardForm<LabWork>, nonUserMode {
    private String[] args;
    private Scanner scanner;
    private final Console console;

    public LabWorkFormNonUserMode(Console console) {
        this.console = console;
    }

    public LabWork build() {
        LabWork labWork = new LabWork();

        getCreationDate(labWork);

        getCreationId(labWork);

        getName(labWork);

        getCoordinates(labWork);

        getMinimalPoint(labWork);

        getMaximumPoint(labWork);

        getPersonalQualitiesMinimum(labWork);

        getDifficulty(labWork);

        getDiscipline(labWork);

        return labWork;
    }

    public void getCreationId(LabWork labWork) {
        IdMustMoreThanZero idMustMoreThanZero = new IdMustMoreThanZero();
        while (true) {
            long id = IdManager.generateId();
            if (idMustMoreThanZero.validate(id)) {
                labWork.setId(id);
                break;
            }
        }
    }

    public void getCreationDate(LabWork labWork) {
        LocalDate date = LocalDate.now();
        LocalDateValidate localDateValidate = new LocalDateValidate();
        if (localDateValidate.validate(date)) {
            labWork.setCreationDate(date);
        }
    }

    public void getName(LabWork labWork) {
        Validate<String> nameValidate = new NameValidate();
        scanner = new Scanner(args[0]);
        while (true) {
            String name = Reader.inputStringValue(nameValidate, scanner);
            if (!name.isBlank()) {
                labWork.setName(name);
                break;
            } else {
                console.print("Введите название лабораторной(\"String\" != \"\"): " + "\"String\" != \"null\"): ");
                scanner = new Scanner(System.in);
            }
        }
    }

    public void getMinimalPoint(LabWork labWork) {
        Validate<Double> minimalPointValidate = new MinimalPointValidate();
        scanner = new Scanner(args[3]);
        while (true) {
            double res = Reader.inputDoubleValue(minimalPointValidate, scanner);
            if (res != 0) {
                labWork.setMinimalPoint(res);
                break;
            } else {
                console.print("Введите минимальный балл(\"double\" > 0): ");
                scanner = new Scanner(System.in);
            }
        }
    }

    public void getMaximumPoint(LabWork labWork) {
        Validate<Double> getMaximumPoint = new MaximumPointValidate();
        scanner = new Scanner(args[4]);
        while (true) {
            double res = Reader.inputDoubleValue(getMaximumPoint, scanner);
            if (res > labWork.getMinimalPoint()) {
                labWork.setMaximumPoint(res);
                break;
            } else {
                console.println("Значение максимального балла должно быть больше чем значение минимального балла!");
                console.print("Введите максимальный балл(\"Double\" > 0 и \"Double\" != \"null\"): ");
                scanner = new Scanner(System.in);
            }
        }
    }

    public void getPersonalQualitiesMinimum(LabWork labWork) {
        Validate<Integer> integerValidate = new PersonalQualitiesMinimum();
        scanner = new Scanner(args[5]);
        while (true) {
            Integer value = Reader.inputIntValue(integerValidate, scanner);
            if (value <= labWork.getMaximumPoint() && value >= labWork.getMinimalPoint()) {
                labWork.setPersonalQualitiesMinimum(value);
                break;
            } else {
                console.println("Значение должно быть > " + labWork.getMinimalPoint() + " и < " + labWork.getMaximumPoint());
                console.print("Введите балл(\"Integer\" > 0): ");
                scanner = new Scanner(System.in);
            }
        }
    }

    public void getCoordinates(LabWork labWork) {
        CoordinatesFormNonUserMode coordinatesForm = new CoordinatesFormNonUserMode(console);
        String[] arguments = {args[1], args[2]};
        coordinatesForm.setArgs(arguments);
        CoordinateCantBeNull coordinateCantBeNull = new CoordinateCantBeNull();
        Coordinates res = coordinatesForm.build();
        if (coordinateCantBeNull.validate(res)) {
            labWork.setCoordinates(res);
        }
        labWork.setCoordinates(coordinatesForm.build());
    }

    public void getDifficulty(LabWork labWork) {
        DifficultyFormNonUserMode difficultyFormNonUserMode = new DifficultyFormNonUserMode(console);
        String[] diffArgs = {args[6]};
        difficultyFormNonUserMode.setArgs(diffArgs);
        labWork.setDifficulty(difficultyFormNonUserMode.build());
    }

    public void getDiscipline(LabWork labWork) {
        DisciplineFormNonUserMode disciplineFormNonUserMode = new DisciplineFormNonUserMode(console);
        String[] discArgs = {args[7], args[8]};
        disciplineFormNonUserMode.setArgs(discArgs);
        labWork.setDiscipline(disciplineFormNonUserMode.build());
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}