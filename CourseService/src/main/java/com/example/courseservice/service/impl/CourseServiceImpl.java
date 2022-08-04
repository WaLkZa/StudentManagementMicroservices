package com.example.courseservice.service.impl;

import com.example.courseservice.client.TeacherServiceFeignClient;
import com.example.courseservice.exception.CourseNotFoundException;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.model.dto.CourseRequest;
import com.example.courseservice.model.dto.CourseResponse;
import com.example.courseservice.model.dto.CourseTeacherRequest;
import com.example.courseservice.model.dto.TeacherResponse;
import com.example.courseservice.model.entity.Course;
import com.example.courseservice.repository.CourseRepository;
import com.example.courseservice.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;
    private final CourseMapper courseMapper;
    private final TeacherServiceFeignClient teacherServiceFeignClient;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper, TeacherServiceFeignClient teacherServiceFeignClient, CourseMapper courseMapper, TeacherServiceFeignClient teacherServiceFeignClient1) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.courseMapper = courseMapper;
        this.teacherServiceFeignClient = teacherServiceFeignClient1;
    }

    @Override
    public CourseResponse create(CourseRequest newCourseRequest) {
        Course course = this.mapper.map(newCourseRequest, Course.class);

        Course savedCourse = this.courseRepository.save(course);

        return this.mapper.map(savedCourse, CourseResponse.class);
    }

    @Override
    public CourseResponse addTeacherToCourse(CourseTeacherRequest courseTeacherRequest) {
        Course course = this.courseRepository
                .findById(courseTeacherRequest.getCourseId())
                .orElseThrow(CourseNotFoundException::new);

        // TODO: catch feign exception
        TeacherResponse teacher = this.teacherServiceFeignClient.findById(courseTeacherRequest.getTeacherId());

        course.setTeacherId(courseTeacherRequest.getTeacherId());
        this.courseRepository.save(course);

        return this.courseMapper.courseToCourseResponse(course, teacher);
    }

    @Override
    public List<CourseResponse> getAll() {
        List<Course> courses = this.courseRepository.findAll();

        List<CourseResponse> mappedDto = courses.stream()
                .map(course -> this.mapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());

        return mappedDto;
    }

    @Override
    public CourseResponse getById(Long id) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        return this.mapper.map(course, CourseResponse.class);
    }

    @Override
    public List<CourseResponse> getAllByTeacherId(Long teacherId) {
        List<Course> courses = this.courseRepository.findAllByTeacherId(teacherId);

        List<CourseResponse> mappedDto = courses.stream()
                .map(course -> this.mapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());

        return mappedDto;
    }

    @Override
    public CourseResponse update(Long id, CourseRequest updateCourseRequest) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        course.setName(updateCourseRequest.getName());
        course.setTotalHours(updateCourseRequest.getTotalHours());

        Course savedCourse = this.courseRepository.save(course);

        return this.mapper.map(savedCourse, CourseResponse.class);
    }

    @Override
    public CourseResponse delete(Long id) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        this.courseRepository.deleteById(id);

        return this.mapper.map(course, CourseResponse.class);
    }
}
