package org.example.utility;

import org.example.managers.CommandManager;

import java.util.*;

public class Executor {
    private final CommandManager commandManager;
    private final Console console;

    public Executor(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    /**
     * Выборка команды и запуск
     */
    public void input() {
        console.println("Добро пожаловать!");
        console.println("Чтобы познакомиться со списком команд наберите команду " + "\"help\".");

        while (true) {
            console.printCursor();
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String[] input = line.split(" ");
            ArrayList<String> list = new ArrayList<>();
            for(String str : input){
                if(!str.isBlank()){
                    list.add(str.trim());
                }
            }

            String[] arguments = list.toArray(new String[0]);

            if (arguments[0].trim().equals("execute_script")) {
                commandManager.setUserMode(false);
            }
            commandManager.commandExecute(arguments);
        }
    }
}