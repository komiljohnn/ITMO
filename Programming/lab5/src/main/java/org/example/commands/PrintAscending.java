package org.example.commands;

import org.example.collections.LabWork;
import org.example.managers.CollectionManager;
import org.example.utility.Console;

import java.util.TreeSet;

/**
 * Команда, которая выводит элементы коллекции в отсортированном виде
 */
public class PrintAscending implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public PrintAscending(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        StringBuilder builder = new StringBuilder();
        TreeSet<LabWork> result = collectionManager.sortAscending();
        result.forEach(labWork -> builder.append(labWork).append("\n"));
        console.print(builder.toString());
        if (builder.isEmpty()) {
            console.println("Коллекция пуста");
        }
    }

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public String getDescription() {
        return "вывести элементы коллекции в порядке возрастания";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}