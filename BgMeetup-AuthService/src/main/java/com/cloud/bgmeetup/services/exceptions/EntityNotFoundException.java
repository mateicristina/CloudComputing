package com.cloud.bgmeetup.services.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message + " was not found");
    }
}