package com.kwizera.javaamalitechlabemployeemgtsystem.exceptions;

import com.kwizera.javaamalitechlabemployeemgtsystem.utils.CustomLogger;

public class NullEmployeeDbException extends Exception {
    public NullEmployeeDbException(String message) {
        super(message);
        CustomLogger.log(CustomLogger.LogLevel.ERROR, message);
    }
}
