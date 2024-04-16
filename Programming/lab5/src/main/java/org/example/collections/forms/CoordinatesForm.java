package org.example.collections.forms;

import org.example.collections.Coordinates;
import org.example.collections.validators.CoordinateXValidate;
import org.example.collections.validators.CoordinateYValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

public class CoordinatesForm implements StandardForm<Coordinates> {
    private final Console console;

    public CoordinatesForm(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() {
        Scanner scanner = new Scanner(System.in);
        Coordinates coordinates = new Coordinates();
        Reader.setConsole(console);

        getX(coordinates);

        getY(coordinates);

        return coordinates;
    }

    public void getX(Coordinates coordinates){
        Scanner scanner = new Scanner(System.in);
        Validate<Double> doubleValidate = new CoordinateXValidate();
        while (true) {
            console.print("Введите координату x (double <= 452): ");
            double res = Reader.inputDoubleValue(doubleValidate, scanner);
            if (res != 0) {
                coordinates.setX(res);
                break;
            }
        }
    }

    public void getY(Coordinates coordinates){
        Scanner scanner = new Scanner(System.in);
        Validate<Float> floatValidate = new CoordinateYValidate();
        while (true) {
            console.print("Введите координату y(\"Float\" != \"null\"): ");
            float floatRes = Reader.inputFloatValue(floatValidate, scanner);
            if (floatRes != 0) {
                coordinates.setY(floatRes);
                break;
            }

        }
    }

}