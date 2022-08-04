package com.example.teacherservice.client;

import com.example.teacherservice.model.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "CourseService", url = "${course-service.url}")
public interface CourseServiceFeignClient {

    @GetMapping(value="/teacher/{teacherId}")
    List<CourseResponse> findAllByTeacherId(@PathVariable Long teacherId);
}
