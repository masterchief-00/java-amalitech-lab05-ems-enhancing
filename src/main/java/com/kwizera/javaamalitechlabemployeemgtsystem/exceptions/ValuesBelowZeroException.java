package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;

public class ValuesBelowZeroException extends Exception {
    public ValuesBelowZeroException(String message) {
        super(message);
        CustomLogger.log(CustomLogger.LogLevel.ERROR, message);
    }
}
