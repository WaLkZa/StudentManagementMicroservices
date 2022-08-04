package com.example.assingmentservice.controller;

import com.example.assingmentservice.model.dto.AssignmentRequest;
import com.example.assingmentservice.model.dto.AssignmentResponse;
import com.example.assingmentservice.model.dto.AssignmentWithGradeRequest;
import com.example.assingmentservice.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    private ResponseEntity<AssignmentResponse> create(@RequestBody AssignmentRequest assignmentRequest){
        AssignmentResponse savedAssignment = this.assignmentService.create(assignmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignment);
    }

    @GetMapping("/average-in-course/{courseId}")
    public ResponseEntity<Double> getAverageInCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok().body(this.assignmentService.getAverageInCourse(courseId));
    }

    @GetMapping("/average-for-student/{studentId}")
    public ResponseEntity<Double> getAverageForStudent(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok().body(this.assignmentService.getAverageForStudent(studentId));
    }

    @PutMapping("/add-grade")
    public ResponseEntity<AssignmentResponse> addGradeForStudentInCourse(@RequestBody AssignmentWithGradeRequest newGrade) {
        return ResponseEntity.ok().body(this.assignmentService.addGradeForStudentInCourse(newGrade));
    }
}
