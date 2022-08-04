package com.example.courseservice.client;

import com.example.courseservice.model.dto.TeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TeacherService", url = "${teacher-service.url}")
public interface TeacherServiceFeignClient {

    @GetMapping(value="/{teacherId}")
    TeacherResponse findById(@PathVariable Long teacherId);
}
