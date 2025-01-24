package com.example.ProjectElearning.Repository;
import com.example.ProjectElearning.Model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Course findByCoursename(String coursename);
}

