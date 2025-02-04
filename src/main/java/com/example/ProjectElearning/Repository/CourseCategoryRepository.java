package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
}

