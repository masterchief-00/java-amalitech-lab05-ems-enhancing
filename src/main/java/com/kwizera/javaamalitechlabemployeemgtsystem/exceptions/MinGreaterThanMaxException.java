package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;

public class MinGreaterThanMaxException extends Exception {
    public MinGreaterThanMaxException(String message) {
        super(message);
        CustomLogger.log(CustomLogger.LogLevel.ERROR, message);
    }
}
