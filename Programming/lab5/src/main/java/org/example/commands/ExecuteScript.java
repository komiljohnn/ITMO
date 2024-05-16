package org.example.commands;

import org.example.Main;
import org.example.managers.CommandManager;
import org.example.utility.Console;

import java.io.*;
import java.util.*;

/**
 * Команда, которая выполняет все команды записанные в скрипте
 */
public class ExecuteScript implements Command {
    private final CommandManager commandManager;
    private final Console console;

    public ExecuteScript(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        console.println("Выполнение скрипта...\n");
        try {
            String path = Main.PATH + arguments[0].trim();
            if (haveRecursion(path, new LinkedList<>())) {
                console.printError("Обнаружена рекурсия, исправьте ее и попробуйте снова");
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                ArrayList<String> lines = new ArrayList<>();
                String command;
                while ((command = bufferedReader.readLine()) != null) {
                    lines.add(command);
                }
                String[] commands = lines.toArray(new String[0]);
                HashMap<String, String[]> map = new HashMap<>();
                for (int i = 0; i < commands.length; i++) {
                    String[] tmp = commands[i].split(" ");
                    if (tmp[0].equals("add") || tmp[0].equals("add_if_min") || tmp[0].equals("update_id") || tmp[0].equals("remove_lower")) {
                        if (commands.length < (i + 10)) {
                            console.printError("Нехватка данных");
                            break;
                        } else {
                            String[] args = new String[9];
                            int count = 0;
                            for (int j = (i + 1); j < (i + 10); j++) {
                                args[count] = commands[j];
                                count++;
                            }
                            map.put(tmp[0], args);
                            i += 8;
                        }
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
            }
        } catch (FileNotFoundException e) {
            console.printError("Вы ввели не существующий файл");
        } catch (IOException ex) {
            console.printError("Нет доступа к файлу");
        } catch (ArrayIndexOutOfBoundsException e) {
            console.printError("А имя файла?");
        }
        commandManager.setUserMode(true);
    }

    public boolean haveRecursion(String path, LinkedList<String> stack) {
        if (stack.contains(path)) {
            return true;
        }
        stack.addLast(path);
        ArrayDeque<String> listOfPath = new ArrayDeque<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while (true) {
                String line;
                if ((line = bufferedReader.readLine()) == null) {
                    break;
                } else {
                    if (line != null) {
                        String[] tmp = line.split(" ");
                        if (tmp[0].equals("execute_script")) {
                            listOfPath.addLast(tmp[0] + " " + tmp[1]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            console.printError("Не смогли найти файл");
        } catch (IOException e) {
            console.printError("Не хватает прав для чтения файла");
        }

        while (!listOfPath.isEmpty()) {
            String[] arr = listOfPath.pollLast().split(" ");
            String tmpPath = Main.PATH + arr[1];
            if (haveRecursion(tmpPath, stack)) {
                return true;
            }
        }
        stack.removeLast();
        return false;
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