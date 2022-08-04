package com.example.courseservice.service;

import com.example.courseservice.model.dto.CourseRequest;
import com.example.courseservice.model.dto.CourseResponse;
import com.example.courseservice.model.dto.CourseTeacherRequest;

import java.util.List;

public interface CourseService {

    CourseResponse create(CourseRequest newCourseRequest);

    CourseResponse addTeacherToCourse(CourseTeacherRequest courseTeacherRequest);

    List<CourseResponse> getAll();

    CourseResponse getById(Long id);

    List<CourseResponse> getAllByTeacherId(Long teacherId);

    CourseResponse update(Long id, CourseRequest updateCourseRequest);

    CourseResponse delete(Long id);
}
