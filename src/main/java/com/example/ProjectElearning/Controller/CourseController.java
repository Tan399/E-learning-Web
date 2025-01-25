package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Service.CourseCategoryService;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCategoryService courseCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        Course course=new Course();
        User instructor=userService.getUserById(courseDTO.getInstructorId());
        CourseCategory courseCategory1=courseCategoryService.getCategoryById(courseDTO.getCategoryId());
        course.setCategoryId(courseCategory1);
        course.setInstructorId(instructor);
        instructor.getCourses().add(course);
        courseCategory1.getCourses().add(course);
        course.setCoursename(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setLevel(courseDTO.getLevel());



        return new ResponseEntity<>( courseService.createCourse(course), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses().stream().map((existingCourse)->{
            CourseResponseDTO courseDTO=new CourseResponseDTO();
            courseDTO.setCoursename(existingCourse.getCoursename());
            courseDTO.setDescription(existingCourse.getDescription());
            courseDTO.setLevel(existingCourse.getLevel());
            courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
            courseDTO.setCategoryId(existingCourse.getCategoryId().getId());
            courseDTO.setCourseid(existingCourse.getCourseid());
            return courseDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        Course existingCourse = courseService.getCourseById(id);
        CourseResponseDTO courseDTO=new CourseResponseDTO();
        courseDTO.setCoursename(existingCourse.getCoursename());
        courseDTO.setDescription(existingCourse.getDescription());
        courseDTO.setLevel(existingCourse.getLevel());
        courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
        courseDTO.setCategoryId(existingCourse.getCategoryId().getId());
        courseDTO.setCourseid(existingCourse.getCourseid());
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/coursename/{coursename}")
    public ResponseEntity<CourseResponseDTO> getCourseByCourseName(@PathVariable String coursename) {
        Course existingCourse = courseService.getCourseByCourseName(coursename);
        CourseResponseDTO courseDTO=new CourseResponseDTO();
        courseDTO.setCoursename(existingCourse.getCoursename());
        courseDTO.setDescription(existingCourse.getDescription());
        courseDTO.setLevel(existingCourse.getLevel());
        courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
        courseDTO.setCategoryId(existingCourse.getCategoryId().getId());
        courseDTO.setCourseid(existingCourse.getCourseid());
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,@Valid @RequestBody CourseDTO courseDTO) {

        Course existingCourse = courseService.getCourseById(id);
        if (courseDTO.getCourseName() != null && !courseDTO.getCourseName().isEmpty()) {
            existingCourse.setCoursename(courseDTO.getCourseName());
        }
        if (courseDTO.getDescription() != null && !courseDTO.getDescription().isEmpty()) {
            existingCourse.setDescription(courseDTO.getDescription());
        }
        if (courseDTO.getLevel() != null && !courseDTO.getLevel().isEmpty()) {
            existingCourse.setLevel(courseDTO.getLevel());
        }

        if (courseDTO.getInstructorId() != null) {
            User instructor = userService.getUserById(courseDTO.getInstructorId());

            existingCourse.setInstructorId(instructor);
        }

        if (courseDTO.getCategoryId() != null) {
            CourseCategory category = courseCategoryService.getCategoryById(courseDTO.getCategoryId());

            existingCourse.setCategoryId(category);
        }

        Course updatedCourse = courseService.updateCourse(existingCourse);

        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            throw new ResourceNotFoundException("Course with ID " + id + " not found for deletion");
        }
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
