package org.example.collections.forms;

import org.example.collections.Coordinates;
import org.example.collections.LabWork;
import org.example.collections.validators.*;
import org.example.managers.IdManager;
import org.example.utility.Console;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс, который создает объекты LabWork
 */
public class LabWorkForm implements StandardForm<LabWork> {
    private Scanner scanner;
    private final Console console;

    public LabWorkForm(Console console) {
        this.console = console;
    }

    @Override
    public LabWork build() {
        LabWork labWork = new LabWork();
        Reader reader = new Reader(console, scanner);

        getCreationId(labWork);

        getCreationDate(labWork);

        getName(labWork, reader);

        getCoordinates(labWork);

        getMinimalPoint(labWork, reader);

        getMaximumPoint(labWork, reader);

        getPersonalQualitiesMinimum(labWork, reader);

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

    public void getName(LabWork labWork, Reader reader) {
        Validate<String> nameValidate = new NameValidate();
        scanner = new Scanner(System.in);
        while (true) {
            console.print("Введите название лабораторной(\"String\" != \"\" и " + "\"String\" != \"null\"): ");
            String name = reader.inputStringValue(nameValidate, scanner);
            if (name == null || name.isBlank()) {
                console.println("Нельзя вводить пустое значение!");
            } else {
                labWork.setName(name);
                break;
            }
        }
    }

    public void getMinimalPoint(LabWork labWork, Reader reader) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Validate<Double> minimalValidate = new MinimalPointValidate();
                console.print("Введите минимальный балл(\"double\" > 0): ");
                Double res = reader.inputDoubleValue(minimalValidate, scanner);
                if (res != null) {
                    labWork.setMinimalPoint(res);
                    break;
                } else {
                    console.println("Ваше значение < 0");
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }

    }

    public void getMaximumPoint(LabWork labWork, Reader reader) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Validate<Double> maximumValidate = new MaximumPointValidate();
                console.print("Введите максимальный балл(\"Double\" > 0 и \"Double\" != \"null\"): ");
                Double res = reader.inputDoubleValue(maximumValidate, scanner);
                if (res <= labWork.getMinimalPoint()) {
                    console.println("Значение максимального балла должно быть больше чем значение минимального балла!");
                } else if (res != null) {
                    labWork.setMaximumPoint(res);
                    break;
                } else {
                    console.println("Ваше значение <= 0");
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }
    }

    public void getPersonalQualitiesMinimum(LabWork labWork, Reader reader) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Validate<Integer> personalQualitiesMinimum = new PersonalQualitiesMinimum();
                console.print("Введите балл(\"Integer\" > 0): ");
                Integer res = reader.inputIntValue(personalQualitiesMinimum, scanner);
                if (res == null || (res <= labWork.getMaximumPoint() && res >= labWork.getMinimalPoint())) {
                    labWork.setPersonalQualitiesMinimum(res);
                    break;
                } else if (res == 0) {
                    console.println("Ваше значение < 1");
                } else {
                    console.println("Значение должно быть > " + labWork.getMinimalPoint() + " и < " + labWork.getMaximumPoint());
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }
    }

    public void getCoordinates(LabWork labWork) {
        CoordinatesForm coordinates = new CoordinatesForm(console, scanner);
        Coordinates res = coordinates.build();
        CoordinateCantBeNull coordinateCantBeNull = new CoordinateCantBeNull();
        if (coordinateCantBeNull.validate(res)) {
            labWork.setCoordinates(res);
        }
    }

    public void getDifficulty(LabWork labWork) {
        DifficultyForm difficultyForm = new DifficultyForm(console, scanner);
        labWork.setDifficulty(difficultyForm.build());
    }

    public void getDiscipline(LabWork labWork) {
        DisciplineForm disciplineForm = new DisciplineForm(console, scanner);
        labWork.setDiscipline(disciplineForm.build());
    }

}