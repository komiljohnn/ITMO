package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.FileManager;
import org.example.collections.CollectionForParse;
import org.example.utility.Console;

import java.time.LocalDateTime;

/**
 * Команда, которая записывает коллекцию в файл
 */
public class Save implements Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final Console console;

    public Save(CollectionManager collectionManager, FileManager fileManager, Console console) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        CollectionForParse collectionForParse = new CollectionForParse();
        collectionForParse.setList(collectionManager.getCollection());
        fileManager.parseCollectionToXml(collectionForParse, (fileManager.getFilePath()));
        collectionManager.setLastSaveTime(LocalDateTime.now());
        console.println("Коллекция записана в файл");
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}