package org.example.commands;

import org.example.collections.LabWork;
import org.example.managers.CollectionManager;
import org.example.utility.Console;

/**
 * Команда sum_of_minimal_point
 */
public class SumOfMinimalPoint implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public SumOfMinimalPoint(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выводит сумму минимальных значений для всех элементов коллекции
     * @param arguments Аргументы
     */
    @Override
    public void execute(String[] arguments) {
        double result = 0.0;
        for (LabWork labWork : collectionManager.getCollection()) {
            result += labWork.getMinimalPoint();
        }
        console.println(result);
    }

    @Override
    public String getName() {
        return "sum_of_minimal_point";
    }

    @Override
    public String getDescription() {
        return "вывести сумму значений поля minimalPoint для всех элементов коллекции";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}