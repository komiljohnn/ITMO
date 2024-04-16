package org.example.commands;

import org.example.collections.LabWork;
import org.example.collections.forms.LabWorkForm;
import org.example.collections.forms.StandardForm;
import org.example.collections.forms.nonUserMode.LabWorkFormNonUserMode;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.utility.Console;

public class RemoveLower implements Command {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final Console console;

    public RemoveLower(CollectionManager collectionManager, CommandManager commandManager, Console console) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        StandardForm<LabWork> labWorkForm;
        if (commandManager.isUserMode()) {
            labWorkForm = new LabWorkForm(console);
        } else {
            LabWorkFormNonUserMode nonUserMode = new LabWorkFormNonUserMode(console);
            nonUserMode.setArgs(arguments);
            labWorkForm = nonUserMode;
        }
        LabWork labWork = labWorkForm.build();
        collectionManager.removeLower(labWork);
        console.println("Удалены все элементы меньше чем заданный!");
    }

    @Override
    public String getName() {
        return "remove_lower{element}";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}