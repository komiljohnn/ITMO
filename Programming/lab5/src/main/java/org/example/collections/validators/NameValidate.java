package org.example.collections.validators;

public class NameValidate implements Validate<String> {

    @Override
    public boolean validate(String line) {
        return line != null && !line.isEmpty();
    }

    @Override
    public String getDescription() {
        return "name validate";
    }

}