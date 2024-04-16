package org.example.commands;

import org.example.collections.LabWork;
import org.example.collections.forms.LabWorkForm;
import org.example.collections.forms.StandardForm;
import org.example.collections.forms.nonUserMode.LabWorkFormNonUserMode;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.utility.Console;

import java.util.TreeSet;

public class AddIfMin implements Command {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final Console console;

    public AddIfMin(CollectionManager collectionManager, CommandManager commandManager, Console console) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        StandardForm<LabWork> labWork;
        TreeSet<LabWork> sortedSet = collectionManager.sortAscending();
        if (commandManager.isUserMode()) {
            labWork = new LabWorkForm(console);
        } else {
            LabWorkFormNonUserMode nonUserMode = new LabWorkFormNonUserMode(console);
            nonUserMode.setArgs(arguments);
            labWork = nonUserMode;
        }
        LabWork result = labWork.build();

        if (result.getPersonalQualitiesMinimum() < sortedSet.first().getPersonalQualitiesMinimum()) {
            collectionManager.addToCollection(result);
            console.println("Объект добавлен");
        } else {
            console.println("Объект больше чем наименьший элемент");
        }
    }

    @Override
    public String getName() {
        return "add_if_min{element}";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}