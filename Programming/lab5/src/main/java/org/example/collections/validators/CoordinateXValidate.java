package org.example.collections.validators;

public class CoordinateXValidate implements Validate<Double> {
    @Override
    public boolean validate(Double x) {
        return x <= 452;
    }

    @Override
    public String getDescription() {
        return "Validate coordinate x";
    }

}