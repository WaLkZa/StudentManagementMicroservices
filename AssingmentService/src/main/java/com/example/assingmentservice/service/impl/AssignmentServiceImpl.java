package com.example.assingmentservice.service.impl;

import com.example.assingmentservice.mapper.AssignmentMapper;
import com.example.assingmentservice.model.dto.AssignmentRequest;
import com.example.assingmentservice.model.dto.AssignmentResponse;
import com.example.assingmentservice.model.dto.AssignmentWithGradeRequest;
import com.example.assingmentservice.model.entity.Assignment;
import com.example.assingmentservice.repository.AssignmentRepository;
import com.example.assingmentservice.service.AssignmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final ModelMapper mapper;
    private final AssignmentMapper assignmentMapper;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, ModelMapper mapper, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.mapper = mapper;
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public AssignmentResponse create(AssignmentRequest newAssignmentRequest) {
        Assignment assignment = this.assignmentMapper.assignmentRequestToAssignmentEntity(newAssignmentRequest);

        Assignment savedAssignment = this.assignmentRepository.save(assignment);

        return this.mapper.map(savedAssignment, AssignmentResponse.class);
    }

    @Override
    public AssignmentResponse addGradeForStudentInCourse(AssignmentWithGradeRequest assignmentRequest) {
        Optional<Assignment> assignment = this.assignmentRepository
                .findByStudentIdAndCourseId(assignmentRequest.getStudentId(), assignmentRequest.getCourseId());

        if (assignment.isEmpty()) {
            throw new IllegalStateException("Assignment not found!");
        }

        assignment.get().addGrade(assignmentRequest.getGrade());

        Assignment savedAssignment = this.assignmentRepository.save(assignment.get());

        return this.mapper.map(savedAssignment, AssignmentResponse.class);
    }

    @Override
    public Double getAverageInCourse(Long courseId) {
        List<Assignment> assignments = this.assignmentRepository.findByCourseId(courseId);

        OptionalDouble average = assignments.stream()
                .filter(s -> s.getAverage() > 0.0)
                .mapToDouble(Assignment::getAverage).average();

        return average.getAsDouble();
        //return 0.0;
    }

    @Override
    public Double getAverageForStudent(Long studentId) {
        List<Assignment> assignments = this.assignmentRepository.findByStudentId(studentId);

        OptionalDouble average = assignments.stream()
                .filter(s -> s.getAverage() > 0.01)
                .mapToDouble(Assignment::getAverage).average();

        return average.getAsDouble();
        //return 0.0;
    }
}
