package com.sanjeev.sampleprojects.userservice.service;

public class CustomerNotFoundException extends IllegalArgumentException {

    public CustomerNotFoundException() {

    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
