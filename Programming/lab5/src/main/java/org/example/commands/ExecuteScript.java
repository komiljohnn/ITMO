package org.example.commands;

import org.example.Main;
import org.example.managers.CommandManager;
import org.example.utility.Console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExecuteScript implements Command {
    private final CommandManager commandManager;
    private final Console console;

    public ExecuteScript(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        console.println("Выполнение скрипта...");
        String path = Main.PATH + arguments[0].trim();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            ArrayList<String> lines = new ArrayList<>();
            String command;
            while ((command = bufferedReader.readLine()) != null) {
                if (!command.isBlank()) {
                    lines.add(command);
                }
            }
            String[] commands = lines.toArray(new String[0]);
            HashMap<String, String[]> map = new HashMap<>();
            for (int i = 0; i < commands.length; i++) {
                String[] tmp = commands[i].split(" ");
                if (tmp[0].equals("add") || tmp[0].equals("add_if_min") || tmp[0].equals("update_id") || tmp[0].equals("remove_lower")) {
                    String[] args = new String[9];
                    int count = 0;
                    for (int j = (i + 1); j < (i + 10); j++) {
                        args[count] = commands[j];
                        count++;
                    }
                    map.put(tmp[0], args);
                    i += 8;
                } else {
                    String[] args = new String[tmp.length - 1];
                    System.arraycopy(tmp, 1, args, 0, (tmp.length - 1));
                    map.put(tmp[0].trim(), args);
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                String[] value = (String[]) entry.getValue();
                String[] commandWithArgs = new String[1 + value.length];
                commandWithArgs[0] = key.trim();
                for (int i = 0; i < value.length; i++) {
                    commandWithArgs[i + 1] = value[i].trim();
                }
                commandManager.commandExecute(commandWithArgs);
            }
        } catch (FileNotFoundException e) {
            console.printError("Вы ввели не существующий файл");
        } catch (IOException ex) {
            console.printError("Нет доступа к файлу");
        }
        commandManager.setUserMode(true);
        //TODO: предотвращать ввод неверных данных
    }

    @Override
    public String getName() {
        return "execute_script file_name";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +
                "интерактивном режиме";
    }

    @Override
    public String toString() {
        return getName() + " : " + getDescription();
    }

}