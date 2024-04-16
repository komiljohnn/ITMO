package org.example.commands;

import org.example.collections.LabWork;
import org.example.managers.CollectionManager;
import org.example.utility.Console;

public class AverageOfMinimalPoint implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public AverageOfMinimalPoint(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        double result;
        double sum = 0.0;
        for (LabWork labWork : collectionManager.getCollection()) {
            sum += labWork.getMinimalPoint();
        }
        result = sum / collectionManager.getCollection().size();
        console.println(result);
    }

    @Override
    public String getName() {
        return "average_of_minimal_point";
    }

    @Override
    public String getDescription() {
        return "вывести среднее значение поля minimalPoint для всех элементов коллекции";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}