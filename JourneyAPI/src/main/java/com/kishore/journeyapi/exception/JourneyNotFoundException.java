package com.kishore.journeyapi.exception;

public class JourneyNotFoundException extends RuntimeException {

    public JourneyNotFoundException(String message) {
        super(message);
    }
}