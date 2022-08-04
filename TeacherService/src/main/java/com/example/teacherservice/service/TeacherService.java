package com.example.teacherservice.service;

import com.example.teacherservice.model.dto.TeacherCourseResponse;
import com.example.teacherservice.model.dto.TeacherRequest;
import com.example.teacherservice.model.dto.TeacherResponse;

import java.util.List;

public interface TeacherService {

    TeacherResponse create(TeacherRequest newTeacherRequest);

    List<TeacherResponse> getAll();

    TeacherCourseResponse getAllTeacherCoursesByTeacherId(Long teacherId);

    TeacherResponse getById(Long teacherId);

    TeacherResponse update(Long id, TeacherRequest updateTeacherRequest);

    TeacherResponse delete(Long id);
}
