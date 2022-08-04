package com.example.teacherservice.service.impl;

import com.example.teacherservice.client.CourseServiceFeignClient;
import com.example.teacherservice.exception.TeacherNotFoundException;
import com.example.teacherservice.model.dto.CourseResponse;
import com.example.teacherservice.model.dto.TeacherCourseResponse;
import com.example.teacherservice.model.dto.TeacherRequest;
import com.example.teacherservice.model.dto.TeacherResponse;
import com.example.teacherservice.model.entity.Teacher;
import com.example.teacherservice.repository.TeacherRepository;
import com.example.teacherservice.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;
    private final CourseServiceFeignClient courseServiceFeignClient;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper mapper, CourseServiceFeignClient courseServiceFeignClient) {
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
        this.courseServiceFeignClient = courseServiceFeignClient;
    }

    @Override
    public TeacherResponse create(TeacherRequest newTeacherRequest) {
        Teacher teacher = this.mapper.map(newTeacherRequest, Teacher.class);

        Teacher savedTeacher = this.teacherRepository.save(teacher);

        return this.mapper.map(savedTeacher, TeacherResponse.class);
    }

    @Override
    public List<TeacherResponse> getAll() {
        List<Teacher> teachers = this.teacherRepository.findAll();

        List<TeacherResponse> mappedDto = teachers.stream()
                .map(teacher -> this.mapper.map(teacher, TeacherResponse.class))
                .collect(Collectors.toList());

        return mappedDto;
    }

    @Override
    public TeacherCourseResponse getAllTeacherCoursesByTeacherId(Long teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);

        List<CourseResponse> courses = this.courseServiceFeignClient.findAllByTeacherId(teacherId);

        return TeacherCourseResponse.builder()
                .teacher(this.mapper.map(teacher, TeacherResponse.class))
                .courses(courses)
                .build();
    }

    @Override
    public TeacherResponse getById(Long teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);

        return this.mapper.map(teacher, TeacherResponse.class);
    }

    @Override
    public TeacherResponse update(Long id, TeacherRequest updateTeacherRequest) {
        Teacher teacher = this.teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);

        teacher.setName(updateTeacherRequest.getName());
        teacher.setDegree(updateTeacherRequest.getDegree());

        Teacher savedTeacher = this.teacherRepository.save(teacher);

        return this.mapper.map(savedTeacher, TeacherResponse.class);
    }

    @Override
    public TeacherResponse delete(Long id) {
        Teacher teacher = this.teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);

        this.teacherRepository.deleteById(id);

        return this.mapper.map(teacher, TeacherResponse.class);
    }
}
