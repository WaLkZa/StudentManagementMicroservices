package com.example.courseservice.controller;

import com.example.courseservice.model.dto.CourseRequest;
import com.example.courseservice.model.dto.CourseResponse;
import com.example.courseservice.model.dto.CourseTeacherRequest;
import com.example.courseservice.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest newCourseRequest){
        CourseResponse savedTeacher = this.courseService.create(newCourseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll(){
        return ResponseEntity.ok().body(this.courseService.getAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id){
        CourseResponse response = this.courseService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value="/teacher/{teacherId}")
    public ResponseEntity<List<CourseResponse>> getByTeacherId(@PathVariable Long teacherId){
        List<CourseResponse> response = courseService.getAllByTeacherId(teacherId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Long id, @RequestBody CourseRequest updateCourseRequest){
        CourseResponse updateStudent = this.courseService.update(id, updateCourseRequest);
        return ResponseEntity.ok().body(updateStudent);
    }

    @PutMapping(value="/add-teacher")
    public ResponseEntity<CourseResponse> addTeacherToCourse(@RequestBody CourseTeacherRequest courseTeacherRequest) {
        CourseResponse response = this.courseService.addTeacherToCourse(courseTeacherRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<CourseResponse> delete(@PathVariable Long id){
        CourseResponse deletedTeacher = this.courseService.delete(id);
        return ResponseEntity.ok().body(deletedTeacher);
    }
}
