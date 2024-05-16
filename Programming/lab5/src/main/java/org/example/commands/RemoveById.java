package org.example.commands;


import org.example.managers.CollectionManager;
import org.example.managers.IdManager;
import org.example.utility.Console;

/**
 * Команда, которая удаляет элемент по id
 */
public class RemoveById implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public RemoveById(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        long id = Long.parseLong(arguments[0].trim());
        if (collectionManager.isEmpty()) {
            console.println("Коллекция пуста");
        } else {
            if (collectionManager.deleteById(id)) {
                IdManager.removeIDbyId(id);
                console.println("Элемент удален из коллекции");
            }

        }
    }

    @Override
    public String getName() {
        return "remove_by_id id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}