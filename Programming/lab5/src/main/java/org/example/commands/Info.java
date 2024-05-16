package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.utility.Console;

import java.time.format.DateTimeFormatter;

/**
 * Команда, которая выводит информацию о коллекции
 */
public class Info implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public Info(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(collectionManager.getInitTime());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ");
        String time = timeFormatter.format(collectionManager.getLastSaveTime());
        if (collectionManager.isEmpty()) {
            console.println("Тип : LabWork, дата и время инициализации: " + date + ", время последней модификации: " + time + ", но коллекция пустая");
        } else {
            console.println("Тип : LabWork,\nдата и время инициализации: " + date + ",\nвремя последней модификации: " + time + ",\nколичество элементов " +
                    "коллекции: " + collectionManager.getCollection().size());
        }

    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}