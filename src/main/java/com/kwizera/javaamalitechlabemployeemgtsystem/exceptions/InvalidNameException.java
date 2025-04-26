package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

public class InvalidNameException extends Exception {
    private String invalidInput;

    public InvalidNameException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
