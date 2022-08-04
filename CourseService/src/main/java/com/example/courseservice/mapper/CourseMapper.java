package com.example.courseservice.mapper;

import com.example.courseservice.model.dto.CourseResponse;
import com.example.courseservice.model.dto.TeacherResponse;
import com.example.courseservice.model.entity.Course;

import java.util.List;

public interface CourseMapper {

    CourseResponse courseToCourseResponse(Course course, TeacherResponse teacherResponse);
    List<CourseResponse> courseToCourseResponse(List<Course> courses, TeacherResponse teacherResponse);
}
