package com.example.studentservice.controller;

import com.example.studentservice.model.dto.StudentRequest;
import com.example.studentservice.model.dto.StudentResponse;
import com.example.studentservice.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest newStudentRequest){
        StudentResponse savedTeacher = this.studentService.create(newStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll(){
        return ResponseEntity.ok().body(this.studentService.getAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id){
        StudentResponse response = this.studentService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody StudentRequest updateStudentRequest){
        StudentResponse updateStudent = this.studentService.update(id, updateStudentRequest);
        return ResponseEntity.ok().body(updateStudent);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<StudentResponse> delete(@PathVariable Long id){
        StudentResponse deletedTeacher = this.studentService.delete(id);
        return ResponseEntity.ok().body(deletedTeacher);
    }
}
