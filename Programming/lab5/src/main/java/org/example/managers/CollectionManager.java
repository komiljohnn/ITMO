package org.example.managers;

import org.example.collections.LabWork;
import org.example.collections.comparators.LabWorkComparator;
import org.example.collections.CollectionForParse;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Класс для работы с коллекцией
 */
public class CollectionManager {
    private LinkedList<LabWork> collection;
    private LocalDateTime initTime;
    private LocalDateTime lastSaveTime;

    public CollectionManager() {
        setInitTime(LocalDateTime.now());
        setLastSaveTime(LocalDateTime.now());
        setCollection(new LinkedList<>());
    }

    public void addToCollection(LabWork o) {
        collection.add(o);

    }

    /**
     * Сортирует коллекцию по возрастанию
     * @return Отсортированную коллекцию
     */
    public TreeSet<LabWork> sortAscending() {
        LabWorkComparator labWorkComparator = new LabWorkComparator();
        TreeSet<LabWork> result = new TreeSet<>(labWorkComparator);
        result.addAll(getCollection());
        return result;
    }

    public boolean isEmpty() {
        return getCollection().isEmpty();
    }

    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает элемент по id
     * @param id id
     * @return Возвращает элемент
     */
    public LabWork getById(long id) {
        LabWork labWork = new LabWork();
        for (LabWork lab : collection) {
            if (lab.getId() == id) {
                labWork = lab;
                break;
            } else {
                labWork = null;
            }
        }
        return labWork;
    }

    /**
     * Удаляет элемент по id
     * @param id id
     * @return Возвращает результат удаления элемента
     */
    public boolean deleteById(long id) {
        if (getById(id) != null) {
            LabWork labWork = getById(id);
            IdManager.removeIDbyId(id);
            collection.remove(labWork);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Удаляет все элементы коллекции которые меньше labWork
     * @param labWork элемент(labWork)
     */
    public void removeLower(LabWork labWork) {
        Iterator<LabWork> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getPersonalQualitiesMinimum() < labWork.getPersonalQualitiesMinimum()) {
                iterator.remove();
            }
        }
    }

    /**
     * Заполняет коллекцию с данными из файла
     * @param path Путь до файла
     * @param fileManager Менеджер файлов
     */
    public void loadCollection(String path, FileManager fileManager) {
        CollectionForParse collectionForParse1 = fileManager.parseXmlToCollection(path);
        for (LabWork labWork : collectionForParse1.getList()) {
            IdManager.addById(labWork.getId());
            collection.add(labWork);
        }
        setInitTime(LocalDateTime.now());
    }

    public void setCollection(LinkedList<LabWork> collection) {
        this.collection = collection;
    }

    public LocalDateTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalDateTime initTime) {
        this.initTime = initTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(LocalDateTime lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    public LinkedList<LabWork> getCollection() {
        return collection;
    }

}