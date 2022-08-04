package com.example.teacherservice.exception;

public class TeacherNotFoundException extends CustomRuntimeException {
    public TeacherNotFoundException() {
        super("Teacher does not exists!");
    }
}