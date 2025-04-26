package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

public class InvalidSalaryException extends Exception {
    private String invalidInput;

    public InvalidSalaryException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
