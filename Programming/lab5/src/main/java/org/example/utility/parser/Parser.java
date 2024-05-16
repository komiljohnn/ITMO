package org.example.utility.parser;

import org.example.collections.CollectionForParse;
import org.example.collections.LabWork;
import org.example.utility.Console;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Класс для парсинга
 */
public class Parser {
    public static Console console;

    /**
     * Преобразование объекта в формат XML
     *
     * @param collectionForParse Коллекция которую нужно парсить
     * @return Возвращает данные в формате XML
     */
    public static String marshal(CollectionForParse collectionForParse) {
        String result = "";
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(CollectionForParse.class, LabWork.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(collectionForParse, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            console.printError("Убедитесь что вы используете JAXB из Maven");
        }
        return result;
    }

    /**
     * Преобразование XML данных в объект
     *
     * @param path Путь до XML файла, которую нужно распарсить
     * @return Возвращает объект типа CollectionForParse
     */
    public static CollectionForParse unmarshal(File path) {
        CollectionForParse result = new CollectionForParse();
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionForParse.class, LabWork.class);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            result = (CollectionForParse) jaxbUnmarshaller.unmarshal(bufferedReader);
        } catch (JAXBException e) {
            console.printError("В файле нарушен формат XML");
        } catch (FileNotFoundException f) {
            console.printError("Вы ввели не существующий файл");
        }
        return result;
    }

    /**
     * Присваивает значение к полю console
     *
     * @param console консоль
     */
    public static void setConsole(Console console) {
        Parser.console = console;
    }

}