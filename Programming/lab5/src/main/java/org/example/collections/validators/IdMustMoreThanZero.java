package org.example.collections.validators;

public class IdMustMoreThanZero implements Validate<Long> {
    @Override
    public boolean validate(Long id) {
        return !(id < 1);
    }

    @Override
    public String getDescription() {
        return "Validate LabWork id";
    }

}