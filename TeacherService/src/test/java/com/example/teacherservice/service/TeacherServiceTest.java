package com.example.teacherservice.service;

import com.example.teacherservice.client.CourseServiceFeignClient;
import com.example.teacherservice.repository.TeacherRepository;
import com.example.teacherservice.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseServiceFeignClient courseServiceFeignClient;

    @Mock
    private TeacherServiceImpl teacherServiceImpl;



}


