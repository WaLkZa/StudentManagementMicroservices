package com.example.teacherservice.controller;

import com.example.teacherservice.model.dto.TeacherCourseResponse;
import com.example.teacherservice.model.dto.TeacherRequest;
import com.example.teacherservice.model.dto.TeacherResponse;
import com.example.teacherservice.service.TeacherService;
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
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> create(@RequestBody TeacherRequest newTeacherRequest){
        TeacherResponse savedTeacher = this.teacherService.create(newTeacherRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAll(){
        return ResponseEntity.ok().body(this.teacherService.getAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id){
        TeacherResponse response = this.teacherService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value="/courses/{teacherId}")
    public ResponseEntity<TeacherCourseResponse> getByTeacherId(@PathVariable Long teacherId) {
        TeacherCourseResponse response = this.teacherService.getAllTeacherCoursesByTeacherId(teacherId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest updateTeacherRequest){
        TeacherResponse updatedTeacher = this.teacherService.update(id, updateTeacherRequest);
        return ResponseEntity.ok().body(updatedTeacher);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id){
        TeacherResponse deletedTeacher = this.teacherService.delete(id);
        return ResponseEntity.ok().body(deletedTeacher);
    }
}
