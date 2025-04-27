package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;

public class InvalidSalaryException extends Exception {
    private String invalidInput;

    public InvalidSalaryException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
        CustomLogger.log(CustomLogger.LogLevel.ERROR, message);
    }

    public String getInvalidInput() {
        return invalidInput;
    }
}
