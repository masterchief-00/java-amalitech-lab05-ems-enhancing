package com.kwizera.javaamalitechlabemployeemgtsystem.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger {
    public enum LogLevel {
        INFO, DEBUG, ERROR, WARN
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("LOG > [" + timestamp + "] [" + level + "] " + message);
    }
}
