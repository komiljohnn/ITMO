package org.example.commands;

import org.example.collections.LabWork;
import org.example.collections.forms.LabWorkForm;
import org.example.collections.forms.StandardForm;
import org.example.collections.forms.nonUserMode.LabWorkFormNonUserMode;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.IdManager;
import org.example.utility.Console;

/**
 * Команда, которая обновляет элемент по id
 */
public class UpdateById implements Command {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final Console console;

    public UpdateById(CollectionManager collectionManager, CommandManager commandManager, Console console) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    /**
     * Обновляет элемент коллекции по id
     *
     * @param arguments Аргументы
     */
    @Override
    public void execute(String[] arguments) {
        StandardForm<LabWork> form;
        if (collectionManager.isEmpty()) {
            console.println("Коллекция пуста");
        } else {
            long id = Long.parseLong(arguments[0]);
            if (IdManager.containsId(id)) {
                collectionManager.deleteById(id);
                if (commandManager.isUserMode()) {
                    form = new LabWorkForm(console);
                } else {
                    LabWorkFormNonUserMode nonUserMode = new LabWorkFormNonUserMode(console);
                    nonUserMode.setArgs(arguments);
                    form = nonUserMode;
                }
                LabWork labWork = form.build();
                labWork.setId(id);
                if (labWork != null) {
                    collectionManager.addToCollection(labWork);
                    console.println("Объект обновлен!");
                } else {
                    console.println("Объект не обновлен");
                }

            } else {
                console.println("Такого id не существует");
            }
        }

    }

    @Override
    public String getName() {
        return "update_id{element}";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}