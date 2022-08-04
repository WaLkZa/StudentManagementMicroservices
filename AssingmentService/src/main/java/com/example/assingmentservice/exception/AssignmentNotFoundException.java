package com.example.assingmentservice.exception;

public class AssignmentNotFoundException extends CustomRuntimeException {
    public AssignmentNotFoundException() {
        super("Assignment does not exists!");
    }
}
