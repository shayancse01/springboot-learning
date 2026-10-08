package com.shayancse.SecurityApp.module6SecurityApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}