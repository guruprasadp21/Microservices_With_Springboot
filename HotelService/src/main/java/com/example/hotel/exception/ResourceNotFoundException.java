package com.example.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String s) {
        super();
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}