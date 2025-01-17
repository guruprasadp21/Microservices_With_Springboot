package com.example.userService.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found in the server!!");
    }

    public ResourceNotFoundException(String message) {

        super(message);
    }
}
