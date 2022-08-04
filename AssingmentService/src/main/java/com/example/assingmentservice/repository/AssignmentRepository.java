package com.example.assingmentservice.repository;

import com.example.assingmentservice.model.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByStudentId(Long studentId);

    List<Assignment> findByCourseId(Long courseId);

    Optional<Assignment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query(nativeQuery = true, value = "SELECT AVG(g.grade) FROM grades g\n" +
            "WHERE g.course_id = ?1\n" +
            "GROUP BY g.course_id")
    Double findAverageGradeInCourse(long courseId);

    @Query(nativeQuery = true, value = "SELECT AVG(g.grade) FROM grades g\n" +
            "WHERE g.student_id = ?1\n" +
            "GROUP BY g.student_id")
    Double findAverageGradeForStudent(Long studentId);
}
