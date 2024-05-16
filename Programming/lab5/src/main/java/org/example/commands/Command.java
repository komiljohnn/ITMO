package org.example.commands;

public interface Command {
    void execute(String[] arguments);

    String getName();

    String getDescription();
}