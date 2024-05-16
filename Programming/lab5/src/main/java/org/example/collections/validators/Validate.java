package org.example.collections.validators;

public interface Validate<T> {
    boolean validate(T t);

    String getDescription();
}