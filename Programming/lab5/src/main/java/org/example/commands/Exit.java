package org.example.commands;

import org.example.utility.Console;

public class Exit implements Command {
    private final Console console;

    public Exit(Console console) {
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        console.println("Выход из приложения...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}