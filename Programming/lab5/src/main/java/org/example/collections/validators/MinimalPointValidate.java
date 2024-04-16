package org.example.collections.validators;

public class MinimalPointValidate implements Validate<Double> {
    @Override
    public boolean validate(Double d) {
        return d >= 1;
    }

    @Override
    public String getDescription() {
        return "Validate minimalPoint";
    }

}