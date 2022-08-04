package com.example.assingmentservice.mapper.impl;

import com.example.assingmentservice.mapper.AssignmentMapper;
import com.example.assingmentservice.model.dto.AssignmentRequest;
import com.example.assingmentservice.model.dto.AssignmentResponse;
import com.example.assingmentservice.model.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapperImpl implements AssignmentMapper {
    @Override
    public Assignment assignmentRequestToAssignmentEntity(AssignmentRequest assignmentRequest) {
        Assignment assignment = new Assignment();
        assignment.setStudentId(assignmentRequest.getStudentId());
        assignment.setCourseId(assignmentRequest.getCourseId());
        return assignment;
    }

    @Override
    public AssignmentResponse assignmentEntityToAssignmentResponse(Assignment assignmentEntity) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setStudentId(assignmentResponse.getStudentId());
        assignmentResponse.setCourseId(assignmentResponse.getCourseId());
        return assignmentResponse;
    }
}
