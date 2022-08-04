package com.example.courseservice.exception;

public class CourseNotFoundException extends CustomRuntimeException{
    public CourseNotFoundException() {
        super("Course does not exists!");
    }
}