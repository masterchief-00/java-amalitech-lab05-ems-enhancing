package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

public class InvalidExperienceYearsException extends Exception {
    private String invalidInput;

    public InvalidExperienceYearsException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
