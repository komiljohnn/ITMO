package org.example;

import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.utility.Console;
import org.example.utility.Executor;

/**
 * Главный класс с которого начинается программа.
 *
 * @author komiljon
 */

public class Main {
    public static final String PATH = "data/";

    public static void main(String[] args) {
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager(console);
        Executor executor = new Executor(commandManager, console);

        commandManager.addCommand("help", new Help(commandManager, console));
        commandManager.addCommand("info", new Info(collectionManager, console));
        commandManager.addCommand("show", new Show(collectionManager, console));
        commandManager.addCommand("add", new Add(collectionManager, commandManager, console));
        commandManager.addCommand("update_id", new UpdateById(collectionManager, commandManager, console));
        commandManager.addCommand("remove_by_id", new RemoveById(collectionManager, console));
        commandManager.addCommand("clear", new Clear(collectionManager, console));
        commandManager.addCommand("save", new Save(collectionManager, fileManager, console));
        commandManager.addCommand("execute_script", new ExecuteScript(commandManager, console));
        commandManager.addCommand("exit", new Exit(console));
        commandManager.addCommand("add_if_min", new AddIfMin(collectionManager, commandManager, console));
        commandManager.addCommand("remove_lower", new RemoveLower(collectionManager, commandManager, console));
        commandManager.addCommand("history", new History(commandManager, console));
        commandManager.addCommand("sum_of_minimal_point", new SumOfMinimalPoint(collectionManager, console));
        commandManager.addCommand("average_of_minimal_point", new AverageOfMinimalPoint(collectionManager, console));
        commandManager.addCommand("print_ascending", new PrintAscending(collectionManager, console));

        collectionManager.loadCollection((fileManager.getFilePath()), fileManager);
        executor.input();

    }
}