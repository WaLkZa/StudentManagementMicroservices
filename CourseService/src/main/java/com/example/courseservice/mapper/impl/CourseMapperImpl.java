package com.example.courseservice.mapper.impl;

import com.example.courseservice.client.TeacherServiceFeignClient;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.model.dto.CourseResponse;
import com.example.courseservice.model.dto.TeacherResponse;
import com.example.courseservice.model.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseResponse courseToCourseResponse(Course course, TeacherResponse teacherResponse) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .totalHours(course.getTotalHours())
                .teacher(teacherResponse)
                .build();
    }

    @Override
    public List<CourseResponse> courseToCourseResponse(List<Course> courses, TeacherResponse teacherResponse) {
        return courses
                .stream()
                .map((course) -> courseToCourseResponse(course, teacherResponse))
                .collect(Collectors.toList());
    }
}
