package org.example.collections.validators;

public class CoordinateYValidate implements Validate<Float> {
    @Override
    public boolean validate(Float y) {
        return y != null;
    }

    @Override
    public String getDescription() {
        return "Validate y coordinate";
    }

}