package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    Optional<CourseCategory> findById(Long id);
}

