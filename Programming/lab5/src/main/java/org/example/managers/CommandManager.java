package org.example.managers;

import org.example.commands.*;

import java.util.*;

/**
 * Класс для управления командами
 */
public class CommandManager {
    /**
     * Map для хранения всех названий и команд
     */
    private final TreeMap<String, Command> commandsMap;
    /**
     * List для хранения истории команд
     */
    private final LinkedList<Command> history;
    /**
     * Поле для хранения режима команд
     */
    private boolean userMode;

    public CommandManager() {
        commandsMap = new TreeMap<>();
        history = new LinkedList<>();
        userMode = true;
    }

    /**
     * Выполнение команды
     * @param line аргументы команды
     */
    public void commandExecute(String[] line) {
        String[] args = new String[line.length - 1];
        System.arraycopy(line, 1, args, 0, (line.length - 1));
        if (containsCommand(line[0].trim())) {
            commandsMap.get(line[0]).execute(args);
            addToHistory(getMap().get(line[0].trim()));
        }
    }

    public boolean containsCommand(String command){
        if(commandsMap.containsKey(command)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isUserMode() {
        return userMode;
    }

    public void setUserMode(boolean userMode) {
        this.userMode = userMode;
    }

    public TreeMap<String, Command> getMap() {
        return commandsMap;
    }

    /**
     * Добавляет в commandsMap новый элемент
     * @param name Название команды
     * @param command Команда
     */
    public void addCommand(String name, Command command) {
        commandsMap.put(name, command);
    }

    public LinkedList<Command> getHistory() {
        return history;
    }

    /**
     * Добавляет команду в историю
     * @param c Команда
     */
    public void addToHistory(Command c) {
        history.add(c);
    }

}