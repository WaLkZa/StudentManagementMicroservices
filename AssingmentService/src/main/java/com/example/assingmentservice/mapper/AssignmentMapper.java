package com.example.assingmentservice.mapper;

import com.example.assingmentservice.model.dto.AssignmentRequest;
import com.example.assingmentservice.model.dto.AssignmentResponse;
import com.example.assingmentservice.model.entity.Assignment;

public interface AssignmentMapper {

    Assignment assignmentRequestToAssignmentEntity(AssignmentRequest assignmentRequest);
    AssignmentResponse assignmentEntityToAssignmentResponse(Assignment assignmentEntity);
}
