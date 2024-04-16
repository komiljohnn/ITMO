package org.example.collections.forms;

import org.example.collections.Difficulty;
import org.example.collections.validators.Validate;
import org.example.utility.Console;

import java.util.Scanner;

public class Reader {
    private static Console console;
    private static boolean mode;

    public static Integer inputIntValue(Validate<Integer> validate, Scanner scanner) {
        int res;
        while (true) {
            String line = scanner.nextLine();
            res = Integer.parseInt(line);
            if (validate.validate(res)) {
                break;
            } else {
                console.print("Ошибка!");
                console.print("Попробуйте еще раз!");
            }
        }
        return res;
    }

    public static Double inputDoubleValue(Validate<Double> validate, Scanner scanner) {
        Double res;
        while (true) {
            String line = scanner.nextLine();
            res = Double.parseDouble(line);
            if (validate.validate(res)) {
                break;
            } else {
                console.print("Ошибка!");
                console.println("Попробуйте еще раз!");
                scanner=new Scanner(System.in);
            }

        }
        return res;
    }

    public static Float inputFloatValue(Validate<Float> validate, Scanner scanner) {
        float res;
        while (true) {
            String line = scanner.nextLine();
            res = Float.parseFloat(line);
            if (validate.validate(res)) {
                break;
            } else {
                console.print("Ошибка!");
                console.print("Попробуйте еще раз!");
            }
        }
        return res;
    }

    public static String inputStringValue(Validate<String> validate, Scanner scanner) {
        String line;
        while (true) {
            line = scanner.nextLine();
            if (validate.validate(line.trim())) {
                break;
            } else {
                console.print("Ошибка!");
                console.print("Попробуйте еще раз!");
            }
        }

        return line.trim();
    }

    public static Long inputLongValue(Scanner scanner, long maxHours) {
        long res = 0;
        long lectureHours = Long.parseLong(scanner.nextLine());
        if (lectureHours != 0 && lectureHours < maxHours) {
            res = lectureHours;
        } else {
            console.print("Ошибка!");
            console.print("Попробуйте еще раз!");
        }
        return res;
    }

    public static Difficulty inputDifficulty(Difficulty[] difficulties, Scanner scanner) {
        Difficulty difficulty;
        difficulty = null;
        String scan = scanner.nextLine();
        String line  = scan.trim();
        boolean isInteger = line.matches("\\d+");

        if(isInteger){
            int num = Integer.parseInt(line);
            for(Difficulty value : difficulties) {
                if (num == value.ordinal()) {
                    difficulty = value;
                    break;
                }
            }
        }else {
            String up = line.toUpperCase();
            for(Difficulty val : difficulties){
                if(up.equals(val.toString())){
                    difficulty = val;
                    break;
                }
            }
        }
        return difficulty;
    }

    public static void setConsole(Console console) {
        Reader.console = console;
    }

    public static boolean isMode() {
        return mode;
    }

    public static void setMode(boolean mode){
        Reader.mode = mode;
    }
}