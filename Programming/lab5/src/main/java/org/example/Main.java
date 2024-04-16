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
    //TODO: подумать как исправить это, потому что сдача будет на гелиосе а там другие пути будут
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
        //TODO: мб добавить это в Executor?

        collectionManager.loadCollection((PATH + "file.xml"), fileManager);
        executor.input();
    }
    //Сейчас чем я занимаюсь
    //==========================================================================================================================
    //TODO: для add/update: если пользовательский ввод неверен, то не заканчиваем выполнение команды, а ждем, пока пользователь не введет правильно.
    //==========================================================================================================================

    //То что нужно решить
    //===================================================================================================================
    //TODO: при неправильном пути до файла в переменных окружения -- запускаемся и пишем в коллекцию, а при попытке сделать save не делаем и сообщаем об ошибке
    //TODO: save/load c несуществующим файлом -- создаем себе этот файл и при необходимости пишем в пустой файл
    //TODO: save/load с файлом без прав доступа -- запускаемся с пустой коллекцией и сообщением о том, что файл пустой. При попытке сохранить не сохраняем и сообщаем об ошибке
    //TODO: Не забываем про рекурсию в скрипте. Захардкодить количество вложенностей -- не решение!
    //TODO: execute_script не должен сообщать о неверной команде в случае, если ему подается пустая строка
    //TODO: add внутри скрипта продолжает считывать поля также внутри скрипта, а не возвращается в консоль
    //TODO: Обработка выхода по комбинации Ctrl-C и Ctrl-D. При этом необходимо производить запись в файл (конечно, если он доступен)!
    //TODO: Команда update сначала проверяет, что id есть, и только ПОТОМ запрашивает данные для замены
    //TODO: Не забываем про человекочитаемые сообщения в случае, если коллекция пуста
    //TODO: исключения! нужно выбросить исключение и обработать их , везде где это нужно.
    //TODO: javadoc до конца написать и сгенерировать
    //===================================================================================================


    //==========================================================================================================================
    //То что я уже сделал.
    //TODO: Давайте использовать сторонние библиотеки для парсинга xml/json, а не изобретать собственный кирпич.
    //TODO: User-friendly prompt на старте программы ('Type help if you don't know', например)
    //TODO: User-friendly prompt для каждой команды (как в bash)
    //TODO: (DONE) для add/update: хорошо бы сообщать пользователю, какого типа данных значение ему вводить и какие на него есть ограничения
    //TODO: (DONE) для add/update: ввод поля enum заглавными буквами, строчными буквами, порядковым номером
    //TODO: (DONE) обновлять время модификации где нужно!
    //TODO: переписать команду history

    //===========================================================================================================================


    //Под конец
    //=========================================================================================
    //TODO: сделать так чтобы код сложно было сломать
    //TODO: запустить и посмотреть как все это работает на гелиосе
    //TODO: оптимизировать максимально насколько это возможно
    //TODO: рефакторинг кода
    //==========================================================================================================================


}