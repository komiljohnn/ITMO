package org.example.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Класс координат
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    private double x; //Максимальное значение поля: 452
    private Float y; //Поле не может быть null

    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates { " + "x = " + getX() + ", y = " + getY() + " }";
    }

}