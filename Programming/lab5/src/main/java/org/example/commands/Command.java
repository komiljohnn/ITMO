package org.example.commands;

public interface Command {
    void execute(String[] arguments);

    String getName();

    String getDescription();
    //TODO : создать еще один интерфейс для команд с аргументами
}