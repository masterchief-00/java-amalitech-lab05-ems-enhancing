package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;

public class InvalidRatingException extends Exception {
    private String invalidInput;

    public InvalidRatingException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
        CustomLogger.log(CustomLogger.LogLevel.ERROR, message);
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
