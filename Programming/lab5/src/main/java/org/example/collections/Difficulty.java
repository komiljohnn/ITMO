package org.example.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Перечисление типов сложности
 */
@XmlAccessorType(XmlAccessType.FIELD)
public enum Difficulty {
    VERY_EASY(0), HARD(1), VERY_HARD(2), IMPOSSIBLE(3), INSANE(4);

    final int num;

    Difficulty(int n) {
        this.num = n;
    }

}