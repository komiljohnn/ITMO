package org.example.collections;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

import org.example.utility.parser.LocalDateAdapter;

/**
 * Класс лабораторная работа
 */
@XmlRootElement(name = "labWork")
@XmlType(name = "labWork", propOrder = {"id", "name", "coordinates", "creationDate", "minimalPoint", "maximumPoint", "personalQualitiesMinimum", "difficulty"
        , "discipline"})
@XmlAccessorType(XmlAccessType.FIELD)
public class LabWork {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double minimalPoint; //Значение поля должно быть больше 0
    private Double maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer personalQualitiesMinimum; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле может быть null

    public LabWork() {

    }

    public LabWork(String name){
        this.name = name;
    }

    public LabWork(long id, String name, Coordinates coordinates, LocalDate creationDate, double minimalPoint, Double maximumPoint,
                   Integer personalQualitiesMinimum, Difficulty difficulty, Discipline discipline) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public Double getMaximumPoint() {
        return maximumPoint;
    }

    public void setMaximumPoint(Double maximumPoint) {
        this.maximumPoint = maximumPoint;
    }

    public Integer getPersonalQualitiesMinimum() {
        return personalQualitiesMinimum;
    }

    public void setPersonalQualitiesMinimum(Integer personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "LabWork { " + "id = " + getId() + ", name = '" + getName() + '\'' + ", coordinates = " + getCoordinates() + ", creationDate = " + getCreationDate() + ", " + "minimalPoint = " + getMinimalPoint() + ", maximumPoint = " + getMaximumPoint() + ", personalQualitiesMinimum = " + getPersonalQualitiesMinimum() + ", " + "difficulty = " + getDifficulty() + ", discipline = " + getDiscipline() + "} ";
    }

}