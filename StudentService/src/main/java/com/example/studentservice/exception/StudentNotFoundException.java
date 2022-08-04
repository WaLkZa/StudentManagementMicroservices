package com.example.studentservice.exception;

public class StudentNotFoundException extends CustomRuntimeException {
    public StudentNotFoundException() {
        super("Student does not exists!");
    }
}