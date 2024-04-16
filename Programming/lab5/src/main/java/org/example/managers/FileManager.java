package org.example.managers;

import org.example.collections.CollectionForParse;
import org.example.utility.Console;
import org.example.utility.parser.Parser;

import java.io.*;

/**
 * Класс для работы с файлом
 */
public class FileManager {
    private final Console console;

    public FileManager(Console console) {
        this.console = console;
        Parser.setConsole(console);
    }

    /**
     * Парсинг XML в коллекцию
     * @param path Путь до XML файла
     * @return Возвращает коллекцию
     */
    public CollectionForParse parseXmlToCollection(String path) {
        return Parser.unmarshal(path);
    }

    /**
     * Парсинг и запись коллекции в XML файл
     * @param collectionForParse Объект который нужно записать
     * @param path Путь до XML файла
     */
    public void parseCollectionToXml(CollectionForParse collectionForParse, String path) {
        try {
            File file = new File(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            String res = Parser.marshal(collectionForParse);
            outputStreamWriter.write(res);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            console.println("Вы ввели не существующий файл");
        } catch (IOException ex) {
            console.println("Нет доступа к файлу");
        }
    }

}