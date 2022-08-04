package com.example.assingmentservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentWithGradeRequest {

    private Long studentId;
    private Long courseId;
    private Double grade;
}
