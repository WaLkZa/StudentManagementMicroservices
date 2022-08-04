package com.example.assingmentservice.service;

import com.example.assingmentservice.model.dto.AssignmentRequest;
import com.example.assingmentservice.model.dto.AssignmentResponse;
import com.example.assingmentservice.model.dto.AssignmentWithGradeRequest;

public interface AssignmentService {

    AssignmentResponse create(AssignmentRequest newAssignmentRequest);

    AssignmentResponse addGradeForStudentInCourse(AssignmentWithGradeRequest assignmentRequest);

    Double getAverageInCourse(Long courseId);

    Double getAverageForStudent(Long studentId);
}
