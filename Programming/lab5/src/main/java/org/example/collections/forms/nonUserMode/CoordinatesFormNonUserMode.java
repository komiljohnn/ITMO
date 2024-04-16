package org.example.collections.forms.nonUserMode;

import org.example.collections.Coordinates;
import org.example.collections.forms.CoordinatesForm;
import org.example.collections.forms.Reader;
import org.example.collections.forms.StandardForm;
import org.example.collections.validators.CoordinateXValidate;
import org.example.collections.validators.CoordinateYValidate;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

public class CoordinatesFormNonUserMode implements StandardForm<Coordinates>, nonUserMode {
    private String[] args;
    private final Console console;

    public CoordinatesFormNonUserMode(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() {
        Coordinates coordinates = new Coordinates();
        Reader.setConsole(console);

        getX(coordinates);

        getY(coordinates);

        return coordinates;
    }

    public void getX(Coordinates coordinates){
        Validate<Double> doubleValidate = new CoordinateXValidate();
        Scanner scanner = new Scanner(args[0]);
        while (true) {
            Double res = Reader.inputDoubleValue(doubleValidate, scanner);
            if (res != null) {
                coordinates.setX(res);
                break;
            } else {
                console.print("Введите значение типа \"Double\" Введите координату x(Максимальное значение поля: 452): ");
            }
        }

    }

    public void getY(Coordinates coordinates){
        Validate<Float> FloatValidate = new CoordinateYValidate();
        Scanner scannerY = new Scanner(args[1]);
        while (true) {
            float resY = Reader.inputFloatValue(FloatValidate, scannerY);
            if (resY != 0) {
                coordinates.setY(resY);
                break;
            } else {
                console.print("Введите координату y(\"Float\" != \"null\"): ");
            }
        }
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

}