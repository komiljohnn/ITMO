package org.example.managers;

import java.util.HashSet;

/**
 * Класс для работы с id
 */
public class IdManager {
    /**
     * Хранилище всех id
     */
    private static final HashSet<Long> idList = new HashSet<>();

    /**
     * Генерирует id
     *
     * @return возвращает id
     */
    public static long generateId() {
        long result = 0;
        while (true) {
            result++;
            if (!idList.contains(result)) {
                idList.add(result);
                break;
            }
        }
        return result;
    }

    /**
     * Удаляет id из хранилища
     *
     * @param id id
     */
    public static void removeIDbyId(long id) {
        idList.remove(id);
    }

    /**
     * Добавляет id в хранилище
     *
     * @param id id
     */
    public static void addById(long id) {
        idList.add(id);
    }

    public static boolean containsId(long id) {
        return idList.contains(id);
    }

}