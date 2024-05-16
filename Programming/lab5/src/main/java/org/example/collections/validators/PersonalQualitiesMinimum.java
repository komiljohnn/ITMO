package org.example.collections.validators;

public class PersonalQualitiesMinimum implements Validate<Integer> {
    @Override
    public boolean validate(Integer i) {
        return i >= 1;
    }

    @Override
    public String getDescription() {
        return "Personal Qualities validate";
    }

}