package org.example.collections.validators;

public class MaximumPointValidate implements Validate<Double> {
    @Override
    public boolean validate(Double d) {
        return d != null && d >= 1;
    }

    @Override
    public String getDescription() {
        return "Maximum point validate";
    }

}