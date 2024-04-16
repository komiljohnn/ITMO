package org.example.collections.forms;

import org.example.collections.Coordinates;
import org.example.collections.LabWork;
import org.example.collections.validators.*;
import org.example.managers.IdManager;
import org.example.utility.Console;

import java.time.LocalDate;
import java.util.Scanner;

public class LabWorkForm implements StandardForm<LabWork> {
    private Scanner scanner;
    private final Console console;

    public LabWorkForm(Console console) {
        this.console = console;
    }

    @Override
    public LabWork build() {
        LabWork labWork = new LabWork();

        getCreationId(labWork);

        getCreationDate(labWork);

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
        scanner = new Scanner(System.in);
        while (true) {
            console.print("Введите название лабораторной(\"String\" != \"\" и " + "\"String\" != \"null\"): ");
            String name = Reader.inputStringValue(nameValidate, scanner);
            if (!name.isBlank()) {
                labWork.setName(name);
                break;
            }
        }
    }

    public void getMinimalPoint(LabWork labWork) {
        Validate<Double> minimalPointValidate = new MinimalPointValidate();
        while (true) {
            console.print("Введите минимальный балл(\"double\" > 0): ");
            double res = Reader.inputDoubleValue(minimalPointValidate, scanner);
            if (res != 0) {
                labWork.setMinimalPoint(res);
                break;
            }
        }
    }

    public void getMaximumPoint(LabWork labWork) {
        Validate<Double> getMaximumPoint = new MaximumPointValidate();
        while (true) {
            console.print("Введите максимальный балл(\"Double\" > 0 и \"Double\" != \"null\"): ");
            double res = Reader.inputDoubleValue(getMaximumPoint, scanner);
            if (res > labWork.getMinimalPoint()) {
                labWork.setMaximumPoint(res);
                break;
            } else {
                console.println("Значение максимального балла должно быть больше чем значение минимального балла!");
            }
        }
    }

    public void getPersonalQualitiesMinimum(LabWork labWork) {
        Validate<Integer> integerValidate = new PersonalQualitiesMinimum();
        while (true) {
            console.print("Введите балл(\"Integer\" > 0): ");
            Integer value = Reader.inputIntValue(integerValidate, scanner);
            if (value <= labWork.getMaximumPoint() && value >= labWork.getMinimalPoint()) {
                labWork.setPersonalQualitiesMinimum(value);
                break;
            } else {
                console.println("Значение должно быть > " + labWork.getMinimalPoint() + " и < " + labWork.getMaximumPoint());
            }
        }
    }

    public void getCoordinates(LabWork labWork) {
        CoordinatesForm coordinates = new CoordinatesForm(console);
        Coordinates res = coordinates.build();
        CoordinateCantBeNull coordinateCantBeNull = new CoordinateCantBeNull();
        if (coordinateCantBeNull.validate(res)) {
            labWork.setCoordinates(res);
        }
    }

    public void getDifficulty(LabWork labWork) {
        DifficultyForm difficultyForm = new DifficultyForm(console);
        labWork.setDifficulty(difficultyForm.build());
    }

    public void getDiscipline(LabWork labWork) {
        DisciplineForm disciplineForm = new DisciplineForm(console);
        labWork.setDiscipline(disciplineForm.build());
    }

}