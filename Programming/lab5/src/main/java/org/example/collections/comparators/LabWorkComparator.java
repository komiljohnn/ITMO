package org.example.collections.comparators;

import org.example.collections.LabWork;

import java.util.Comparator;

public class LabWorkComparator implements Comparator<LabWork> {
    @Override
    public int compare(LabWork o1, LabWork o2) {
        return o1.getPersonalQualitiesMinimum() - o2.getPersonalQualitiesMinimum();
    }
}