package org.example.collections.validators;

import java.time.LocalDate;

public class LocalDateValidate implements Validate<LocalDate> {
    @Override
    public boolean validate(LocalDate date) {
        return date != null;
    }

    @Override
    public String getDescription() {
        return "Date validate";
    }

}