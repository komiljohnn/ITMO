package org.example.collections.forms.nonUserMode;

import org.example.collections.Coordinates;
import org.example.collections.Difficulty;
import org.example.collections.Discipline;
import org.example.collections.LabWork;
import org.example.collections.forms.*;
import org.example.collections.validators.*;
import org.example.managers.IdManager;
import org.example.utility.Console;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс, который создает объекты LabWork, в режиме скрипта
 */
public class LabWorkFormNonUserMode implements StandardForm<LabWork>, nonUserMode {
    private String[] args;
    private Scanner scanner;
    private final Console console;

    public LabWorkFormNonUserMode(Console console) {
        this.console = console;
    }

    public LabWork build() {
        LabWork labWork = new LabWork();
        Reader reader = new Reader(console, scanner);
        boolean create = true;
        getCreationDate(labWork);

        getCreationId(labWork);


        String name = getName(reader);
        if (name != null) {
            labWork.setName(name);
        } else {
            create = false;
        }

        Coordinates coordinates = getCoordinates();
        if (coordinates != null) {
            labWork.setCoordinates(coordinates);
        } else {
            create = false;
        }

        getMinimalPoint(labWork, reader);

        Double max = getMaximumPoint(labWork, reader);
        if (max != null) {
            labWork.setMaximumPoint(max);
        } else if (args[4].isBlank()) {
            create = false;
        } else {
            create = false;
        }

        Integer value = getPersonalQualitiesMinimum(reader);
        if (value == null) {
            console.printError("Поле не может быть пустым или равен null\n");
            create = false;
        } else {
            if (labWork.getMinimalPoint() != null && labWork.getMaximumPoint() != null) {
                console.println("Значение: " + value);
                if (value <= labWork.getMaximumPoint() && value >= labWork.getMinimalPoint()) {
                    labWork.setPersonalQualitiesMinimum(value);
                    console.println("");
                } else {
                    console.printError("Значение должно быть > " + labWork.getMinimalPoint() + " и < " + labWork.getMaximumPoint() + "\n");
                    create = false;
                }
            } else {
                console.printError("Не прошла валидацию\n");
            }
        }

        DifficultyFormNonUserMode difficultyFormNonUserMode = new DifficultyFormNonUserMode(console, scanner);
        String[] diffArgs = {args[6]};
        difficultyFormNonUserMode.setArgs(diffArgs);
        Difficulty tmp = difficultyFormNonUserMode.build();
        if (tmp == null && (!difficultyFormNonUserMode.isCreate())) {
            create = false;
        } else {
            labWork.setDifficulty(tmp);
        }

        Discipline discipline = getDiscipline();
        if (discipline == null) {
            create = false;
        }

        if (!create) {
            labWork = null;
        }
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

    public String getName(Reader reader) {
        Validate<String> nameValidate = new NameValidate();
        scanner = new Scanner(args[0]);
        console.println("Проверка названии...");
        String name = reader.inputStringValue(nameValidate, scanner);
        console.println("Название: " + name);
        if (name == null || name.isBlank()) {
            console.printError("Название не может быть пустой или равно null\n");
            name = null;
        } else {
            console.println("");
        }
        return name;
    }

    public void getMinimalPoint(LabWork labWork, Reader reader) {
        console.println("Проверка минимального значения...");
        Validate<Double> minimalPointValidate = new MinimalPointValidate();
        scanner = new Scanner(args[3]);

        Double res = reader.inputDoubleValue(minimalPointValidate, scanner);
        console.println("Минимальное значение: " + res + "\n");
        if (res != null) {
            labWork.setMinimalPoint(res);
        }

    }

    public Double getMaximumPoint(LabWork labWork, Reader reader) {
        console.println("Проверка максимального значения...");
        Validate<Double> getMaximumPoint = new MaximumPointValidate();
        scanner = new Scanner(args[4]);
        Double res = reader.inputDoubleValue(getMaximumPoint, scanner);
        if (labWork.getMinimalPoint() != null) {
            console.println("Максимальное значение: " + args[4]);
            if (res != null && res < labWork.getMinimalPoint()) {
                console.printError("Значение максимального балла должно быть больше чем значение минимального балла!\n");
                res = null;
            } else if (args[4].isBlank()) {
                console.printError("Максимальное значение не может быть пустой или равно null\n");
            } else {
                console.println("");
            }
        }
        return res;
    }

    public Integer getPersonalQualitiesMinimum(Reader reader) {
        console.println("Проверка значения...");
        Validate<Integer> integerValidate = new PersonalQualitiesMinimum();
        Integer res;
        if (args[5].isBlank()) {
            res = null;
        } else {
            scanner = new Scanner(args[5]);
            res = reader.inputIntValue(integerValidate, scanner);
        }
        return res;
    }

    public Coordinates getCoordinates() {
        CoordinatesFormNonUserMode coordinatesForm = new CoordinatesFormNonUserMode(console, scanner);
        String[] arguments = {args[1], args[2]};
        coordinatesForm.setArgs(arguments);
        CoordinateCantBeNull coordinateCantBeNull = new CoordinateCantBeNull();
        Coordinates res = coordinatesForm.build();
        if (!coordinateCantBeNull.validate(res)) {
            res = null;
        }
        return res;
    }

    public Discipline getDiscipline() {
        DisciplineFormNonUserMode disciplineFormNonUserMode = new DisciplineFormNonUserMode(console, scanner);
        String[] discArgs = {args[7], args[8]};
        disciplineFormNonUserMode.setArgs(discArgs);
        return disciplineFormNonUserMode.build();
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}