package org.example.commands;

import org.example.collections.LabWork;
import org.example.collections.forms.LabWorkForm;
import org.example.collections.forms.StandardForm;
import org.example.collections.forms.nonUserMode.LabWorkFormNonUserMode;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.utility.Console;

public class Add implements Command {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final Console console;

    public Add(CollectionManager collectionManager, CommandManager commandManager, Console console) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void execute(String[] arguments) {
        StandardForm<LabWork> addForm;
        if (commandManager.isUserMode()) {
            addForm = new LabWorkForm(console);
        } else {
            LabWorkFormNonUserMode nonUserMode = new LabWorkFormNonUserMode(console);
            nonUserMode.setArgs(arguments);
            addForm = nonUserMode;
        }
        LabWork labWork = addForm.build();
        collectionManager.addToCollection(labWork);
        console.println("Объект создан!");
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}