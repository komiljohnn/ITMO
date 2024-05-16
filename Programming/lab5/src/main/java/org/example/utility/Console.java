package org.example.utility;

/**
 * Класс для вывода результата в консоль.
 */
public class Console {
    private final String cursor = "# ";

    public void printCursor() {
        print(cursor);
    }

    /**
     * Выводит object в консоль
     *
     * @param object Объект для вывода
     */
    public void print(Object object) {
        System.out.print(object);
    }

    /**
     * Выводит object + "\n" в консоль
     *
     * @param object Объект для вывода
     */
    public void println(Object object) {
        System.out.println(object);
    }

    /**
     * Выводит "Ошибка: " + object в консоль
     *
     * @param object Объект для вывода
     */
    public void printError(Object object) {
        println("Ошибка: " + object);
    }

}