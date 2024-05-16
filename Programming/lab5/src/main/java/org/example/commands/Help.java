package org.example.commands;

import org.example.managers.CommandManager;
import org.example.utility.Console;

/**
 * Команда, которая выводит информацию о командах
 */
public class Help implements Command {
    private final CommandManager commandManager;
    private final Console console;

    public Help(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 0) {
            if (commandManager.containsCommand(arguments[0].trim())) {
                StringBuilder build = new StringBuilder();
                Command command = commandManager.getMap().get(arguments[0].trim());
                build.append(command);
                console.println(build.toString());
            }
        } else {
            StringBuilder builder = new StringBuilder();
            commandManager.getMap().forEach((s, c) -> builder.append(s).append(" : ").append(c.getDescription()).append("\n"));
            console.print(builder.toString());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}