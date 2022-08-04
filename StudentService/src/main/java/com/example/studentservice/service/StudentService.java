package com.example.studentservice.service;

import com.example.studentservice.model.dto.StudentRequest;
import com.example.studentservice.model.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest newStudentRequest);

    List<StudentResponse> getAll();

    StudentResponse getById(Long id);

    StudentResponse update(Long id, StudentRequest updateStudentRequest);

    StudentResponse delete(Long id);
}
