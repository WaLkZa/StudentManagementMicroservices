package com.example.courseservice.model.dto;

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
public class CourseResponse {

    private Long id ;
    private String name;
    private int totalHours;
    private TeacherResponse teacher;
    //private List<StudentGrades> students;
}
