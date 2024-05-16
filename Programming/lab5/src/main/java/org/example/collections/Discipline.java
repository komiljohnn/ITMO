package org.example.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Класс дисциплина
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Discipline {
    @XmlElement(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement(name = "lectureHours")
    private long lectureHours;

    public Discipline(String name, long lectureHours) {
        this.name = name;
        this.lectureHours = lectureHours;
    }

    public Discipline() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    @Override
    public String toString() {
        return "Discipline { " + "name = '" + getName() + '\'' + ", lectureHours = " + getLectureHours() + " } ";
    }

}