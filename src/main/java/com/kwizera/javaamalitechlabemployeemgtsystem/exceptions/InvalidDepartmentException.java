package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

public class InvalidDepartmentException extends Exception {
    private String invalidInput;

    public InvalidDepartmentException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
