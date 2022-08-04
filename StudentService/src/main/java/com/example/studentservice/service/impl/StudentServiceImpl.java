package com.example.studentservice.service.impl;

import com.example.studentservice.exception.StudentNotFoundException;
import com.example.studentservice.model.dto.StudentRequest;
import com.example.studentservice.model.dto.StudentResponse;
import com.example.studentservice.model.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponse create(StudentRequest newStudentRequest) {
        Student student = this.mapper.map(newStudentRequest, Student.class);

        Student savedStudent = this.studentRepository.save(student);

        return this.mapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> students = this.studentRepository.findAll();

        List<StudentResponse> mappedDto = students.stream()
                .map(student -> this.mapper.map(student, StudentResponse.class))
                .collect(Collectors.toList());

        return mappedDto;
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        return this.mapper.map(student, StudentResponse.class);
    }

    @Override
    public StudentResponse update(Long id, StudentRequest updateStudentRequest) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        student.setAge(updateStudentRequest.getAge());
        student.setName(updateStudentRequest.getName());

        Student savedStudent = this.studentRepository.save(student);

        return this.mapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public StudentResponse delete(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        this.studentRepository.deleteById(id);

        return this.mapper.map(student, StudentResponse.class);
    }
}
