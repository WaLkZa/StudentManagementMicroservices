package com.example.assingmentservice.exception;

public abstract class CustomRuntimeException extends RuntimeException {

    protected CustomRuntimeException(String message) {
        super(message);
    }
}