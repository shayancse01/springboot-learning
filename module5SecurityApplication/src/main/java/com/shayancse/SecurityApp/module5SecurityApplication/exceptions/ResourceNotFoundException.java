package com.shayancse.SecurityApp.module5SecurityApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}