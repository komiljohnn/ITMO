package org.example.collections.forms;

import org.example.collections.Difficulty;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

/**
 * Класс, который читает данные из консоли и проверяет
 */
public class Reader {
    private final Console console;
    private final Scanner scanner;

    public Reader(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    public Integer inputIntValue(Validate<Integer> validate, Scanner scanner) {
        Integer res;
        String line = scanner.nextLine();
        res = Integer.parseInt(line);
        if (line.isBlank()) {
            res = null;
        } else if (!validate.validate(res)) {
            console.print("Ошибка!");
            console.println("Попробуйте еще раз!");
        }
        return res;
    }

    public Double inputDoubleValue(Validate<Double> validate, Scanner scanner) {
        Double res = null;
        String line;
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = line.replaceAll(",", ".");
            res = Double.parseDouble(line);
            if (!validate.validate(res) || line.isBlank()) {
                console.printError("Не прошла валидацию");
                res = null;
            }
        }

        return res;
    }

    public Float inputFloatValue(Validate<Float> validate, Scanner scanner) {
        Float res = null;
        String line;
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = line.replaceAll(",", ".");
            res = Float.parseFloat(line);
            if (!validate.validate(res)) {
                console.printError("Не прошла валидацию\n");
                res = null;
            }
        }
        return res;
    }

    public String inputStringValue(Validate<String> validate, Scanner scanner) {
        String line;
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
        } else {
            line = null;
        }
        if (!validate.validate(line)) {
            line = null;
        }
        return line;
    }

    public Long inputLongValue(Scanner scanner, long maxHours) {
        Long res = null;
        String line = scanner.nextLine();
        if (line.isBlank()) {
            console.printError("Нельзя вводить пустое значение");
        } else {
            long lectureHours = Long.parseLong(line);
            if (lectureHours >= 0 && lectureHours < maxHours) {
                res = lectureHours;
            } else {
                console.printError("Не прошла валидацию\n");
                res = null;
            }
        }
        return res;
    }

    public Difficulty inputDifficulty(Difficulty[] difficulties, Scanner scanner) {
        Difficulty difficulty;
        difficulty = null;
        boolean stop = true;
        boolean incorrectOption = false;

        while (stop) {
            String scan = "";
            if (scanner.hasNextLine()) {
                scan = scanner.nextLine();
            }
            String line = scan;
            boolean isInteger = line.matches("-?\\d+");

            if (isInteger) {
                int num = Integer.parseInt(line);
                for (Difficulty value : difficulties) {
                    if (num == value.ordinal()) {
                        difficulty = value;
                        incorrectOption = false;
                        stop = false;
                        break;
                    } else {
                        incorrectOption = true;
                    }
                }

            } else if (line.isBlank()) {
                difficulty = null;
                incorrectOption = false;
                stop = false;
            } else if (!line.isBlank()) {
                String up = line.toUpperCase();
                for (Difficulty val : difficulties) {
                    if (up.equals(val.toString())) {
                        difficulty = val;
                        incorrectOption = false;
                        stop = false;
                        break;
                    } else {
                        incorrectOption = true;
                    }
                }
            }
            if (incorrectOption) {
                console.printError("Вы выбрали несуществующую опцию");
            }

        }
        return difficulty;
    }

}