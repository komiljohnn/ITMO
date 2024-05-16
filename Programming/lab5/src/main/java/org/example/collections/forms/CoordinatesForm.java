package org.example.collections.forms;

import org.example.collections.Coordinates;
import org.example.collections.validators.CoordinateXValidate;
import org.example.collections.validators.CoordinateYValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который создает объекты Coordinates
 */
public class CoordinatesForm implements StandardForm<Coordinates> {
    private final Console console;
    private final Scanner scanner;

    public CoordinatesForm(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public Coordinates build() {
        Scanner scanner = new Scanner(System.in);
        Coordinates coordinates = new Coordinates();
        Reader reader = new Reader(console, scanner);

        getX(coordinates, reader);

        getY(coordinates, reader);

        return coordinates;
    }

    public void getX(Coordinates coordinates, Reader reader) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Validate<Double> doubleValidate = new CoordinateXValidate();
                console.print("Введите координату x (double <= 452): ");
                Double res = reader.inputDoubleValue(doubleValidate, scanner);
                if (res != null) {
                    coordinates.setX(res);
                    break;
                } else {
                    console.println("Ваше значение > 452");
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }
    }

    public void getY(Coordinates coordinates, Reader reader) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Validate<Float> floatValidate = new CoordinateYValidate();
                console.print("Введите координату y(\"Float\" != \"null\"): ");
                Float res = reader.inputFloatValue(floatValidate, scanner);
                if (res != null) {
                    coordinates.setY(res);
                    break;
                } else {
                    console.println("Ваше значение = null");
                }
            } catch (NumberFormatException e) {
                console.printError("Неправильное значение");
            }
        }
    }

}