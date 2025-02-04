package com.example.ProjectElearning.Repository;
import com.example.ProjectElearning.Model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
Course findByCourseid(Long id);


    Course findByCoursename(String coursename);



    @Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT q.course.id FROM Quiz q)")
    List<Course> findCoursesWithoutQuizzes();
}

