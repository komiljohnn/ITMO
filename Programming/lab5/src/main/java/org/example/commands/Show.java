package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.utility.Console;

/**
 * Команда, которая печатает все элементы в консоль
 */
public class Show implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public Show(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        StringBuilder builder = new StringBuilder();
        collectionManager.getCollection().forEach(l -> builder.append(l).append("\n"));
        console.print(builder.toString());
        if (builder.isEmpty()) {
            console.println("Коллекция пуста");
        }
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}