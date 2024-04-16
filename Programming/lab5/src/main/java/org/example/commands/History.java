package org.example.commands;

import org.example.managers.CommandManager;
import org.example.utility.Console;

import java.util.*;

public class History implements Command {
    private final CommandManager commandManager;
    private final Console console;

    public History(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        StringBuilder builder = new StringBuilder();
        if (commandManager.getHistory().isEmpty()) {
            builder.append("Пусто").append("\n");
        } else {
            LinkedList<Command> list = new LinkedList<>(commandManager.getHistory());
            Iterator<Command> iterator = list.iterator();
            int count = 7;
            while(iterator.hasNext() && count != 0){
                count--;
                builder.append(list.pollLast().getName()).append("\n");
            }
        }
        console.print(builder.toString());
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "вывести последние 7 команд (без их аргументов)";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}