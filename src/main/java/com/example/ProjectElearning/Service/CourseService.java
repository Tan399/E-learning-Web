package com.example.ProjectElearning.Service;


import com.example.ProjectElearning.Model.Course;
import com.example.ProjectElearning.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;


    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course getCourseByCourseName(String coursename) {
        return courseRepository.findByCoursename(coursename);
    }


    public Course updateCourse(Course course) {
        Course existingCourse = getCourseById(course.getCourseid());
        if (existingCourse != null) {
            existingCourse.setCoursename(course.getCoursename());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setLevel(course.getLevel());
            existingCourse.setInstructorid(course.getInstructorid());
            existingCourse.setCategory(course.getCategory());
            return courseRepository.save(existingCourse);
        } else {
            return null;
        }
    }


    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
