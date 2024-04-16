package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.utility.Console;

public class Clear implements Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public Clear(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        collectionManager.clear();
        console.println("Коллекция очищена");
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}