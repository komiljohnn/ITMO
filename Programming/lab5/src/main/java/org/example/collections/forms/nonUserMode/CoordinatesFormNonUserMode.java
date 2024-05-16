package org.example.collections.forms.nonUserMode;

import org.example.collections.Coordinates;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.collections.validators.CoordinateXValidate;
import org.example.collections.validators.CoordinateYValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Coordinates, в режиме скрипта
 */
public class CoordinatesFormNonUserMode implements StandardForm<Coordinates>, nonUserMode {
    private String[] args;
    private final Console console;
    private final Scanner scanner;

    public CoordinatesFormNonUserMode(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public Coordinates build() {
        Coordinates coordinates = new Coordinates();
        Reader reader = new Reader(console, scanner);

        Double x = getX(reader);
        if (x != null) {
            coordinates.setX(x);
        } else {
            coordinates = null;
        }

        Float y = getY(reader);
        if (y != null && coordinates != null) {
            coordinates.setY(y);
        } else {
            coordinates = null;
        }

        return coordinates;
    }

    public Double getX(Reader reader) {
        console.println("Проверка координаты x...");
        Validate<Double> doubleValidate = new CoordinateXValidate();
        Scanner scanner = new Scanner(args[0]);
        Double res = reader.inputDoubleValue(doubleValidate, scanner);
        console.println("x: " + args[0]);
        if (res != null) {
            console.println("");
            return res;
        } else {
            console.printError("Не может быть равен null или больше чем 452\n");
            return null;
        }
    }

    public Float getY(Reader reader) {
        console.println("Проверка координаты y...");
        Float resY = 0f;
        try {
            Validate<Float> FloatValidate = new CoordinateYValidate();
            Scanner scannerY = new Scanner(args[1]);
            resY = reader.inputFloatValue(FloatValidate, scannerY);
        } catch (NumberFormatException e) {
            console.println("Нужно вводить значение типа Float");
        }
        console.println("y: " + args[1]);
        if (resY != null) {
            console.println("");
            return resY;
        } else {
            console.printError("Не может быть null\n");
            return null;
        }
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}