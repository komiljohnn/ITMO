package org.example.collections.validators;

import org.example.collections.Coordinates;

public class CoordinateCantBeNull implements Validate<Coordinates> {
    @Override
    public boolean validate(Coordinates o) {
        return o != null;
    }

    @Override
    public String getDescription() {
        return "Validate Coordinates cant be null";
    }

}