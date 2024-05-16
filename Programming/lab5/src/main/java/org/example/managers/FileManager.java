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
    private final File filePath;

    public FileManager(Console console) {
        this.console = console;
        Parser.setConsole(console);
        this.filePath = new File(System.getenv().get("XML"));
    }

    /**
     * Парсинг XML в коллекцию
     *
     * @param path Путь до XML файла
     * @return Возвращает коллекцию
     */
    public CollectionForParse parseXmlToCollection(File path) {
        return Parser.unmarshal(path);

    }

    /**
     * Парсинг и запись коллекции в XML файл
     *
     * @param collectionForParse Объект который нужно записать
     * @param path               Путь до XML файла
     */
    public void parseCollectionToXml(CollectionForParse collectionForParse, File path) {
        try {
            OutputStreamWriter outputStreamWriter;
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
            String res = Parser.marshal(collectionForParse);
            outputStreamWriter.write(res);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            console.println("Вы ввели не существующий файл");
        } catch (IOException ex) {
            console.println("Нет доступа к файлу");
        }
    }

    public File getFilePath() {
        return filePath;
    }

}