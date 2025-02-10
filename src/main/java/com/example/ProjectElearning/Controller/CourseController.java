package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.CourseRepository;
import com.example.ProjectElearning.Repository.EnrollmentRepository;
import com.example.ProjectElearning.Service.CourseCategoryService;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("secure/instructor/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCategoryService courseCategoryService;
    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository er;

    @PostMapping("/upload")
    public ResponseEntity<Course> uploadCourse(
            @RequestPart("CourseDTO") CourseDTO courseDTO,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        System.out.println("entered upload");
        Course course = new Course();
        System.out.println("course name  "+courseDTO.getCoursename());
        User instructor = userService.getUserById(courseDTO.getInstructorId());
        CourseCategory category = courseCategoryService.getCategoryById(courseDTO.getCategoryId());
        System.out.println("category id "+courseDTO.getCategoryId());
        course.setCoursename(courseDTO.getCoursename());
        course.setDescription(courseDTO.getDescription());
        course.setLevel(courseDTO.getLevel());
        course.setPrice(courseDTO.getPrice());
        course.setVideoUrl(courseDTO.getVideoUrl());
        course.setDuration(courseDTO.getDuration());
        course.setInstructorId(instructor);
        course.setCategoryId(category);

        if (file != null && !file.isEmpty()) {
            course.setCourseImage(file.getBytes());
        }

        Course savedCourse = courseService.createCourse(course);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }




    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getCourseImage(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        byte[] image = course.getCourseImage();

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }


    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {

        Course course=new Course();
        User instructor=userService.getUserById(courseDTO.getInstructorId());
        CourseCategory courseCategory1=courseCategoryService.getCategoryById(courseDTO.getCategoryId());
        course.setCategoryId(courseCategory1);
        course.setPrice(courseDTO.getPrice());
        course.setVideoUrl(courseDTO.getVideoUrl());
        course.setInstructorId(instructor);
        instructor.getCourses().add(course);
        courseCategory1.getCourses().add(course);
        course.setCoursename(courseDTO.getCoursename());
        course.setDuration(courseDTO.getDuration());
        course.setDescription(courseDTO.getDescription());
        course.setLevel(courseDTO.getLevel());



        return new ResponseEntity<>( courseService.createCourse(course), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDetails>> getAllCourses() {
        List<CourseDetails> courses = courseService.getAllCourses().stream().map((existingCourse)->{
            CourseDetails courseDTO=new CourseDetails();
            System.out.println("entered getMethod");
            courseDTO.setCoursename(existingCourse.getCoursename());
            courseDTO.setDescription(existingCourse.getDescription());
            courseDTO.setLevel(existingCourse.getLevel());
            courseDTO.setDuration(existingCourse.getDuration());
            courseDTO.setPrice(existingCourse.getPrice());
            courseDTO.setVideoUrl(existingCourse.getVideoUrl());
            courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
            courseDTO.setCategory(existingCourse.getCategoryId().getTitle());
            courseDTO.setCourseid(existingCourse.getCourseid());
            courseDTO.setCourseImage(existingCourse.getCourseImage());
            courseDTO.setEnrolledCount(er.getEnrolledUserCount(existingCourse.getCourseid()));
            System.out.println("image "+existingCourse.getCourseImage());
            return courseDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("instructor/{id}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByInstructorId(@PathVariable Long id) {
        System.out.println("instructor aaala");
        List<CourseResponseDTO> courses = courseService.getAllCourses().stream()
                .filter(existingCourse -> existingCourse.getInstructorId().getUserid().equals(id))
                .map(existingCourse -> {
                    CourseResponseDTO courseDTO = new CourseResponseDTO();
                    courseDTO.setCoursename(existingCourse.getCoursename());
                    courseDTO.setDescription(existingCourse.getDescription());
                    courseDTO.setLevel(existingCourse.getLevel());
                    courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
                    courseDTO.setCategoryId(existingCourse.getCategoryId().getId());
                    courseDTO.setCourseid(existingCourse.getCourseid());
                    courseDTO.setPrice(existingCourse.getPrice());
                    courseDTO.setDuration(existingCourse.getDuration());

                    courseDTO.setVideoUrl(existingCourse.getVideoUrl());
                    courseDTO.setEnrolledCount(er.getEnrolledUserCount(existingCourse.getCourseid()));
                    return courseDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        Course existingCourse = courseService.getCourseById(id);
        CourseResponseDTO courseDTO=new CourseResponseDTO();
        courseDTO.setCoursename(existingCourse.getCoursename());
        courseDTO.setDescription(existingCourse.getDescription());
        courseDTO.setLevel(existingCourse.getLevel());
        courseDTO.setDuration(existingCourse.getDuration());
        courseDTO.setPrice(existingCourse.getPrice());
        courseDTO.setVideoUrl(existingCourse.getVideoUrl());
        courseDTO.setEnrolledCount(er.getEnrolledUserCount(existingCourse.getCourseid()));
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
        courseDTO.setDuration(existingCourse.getDuration());
        courseDTO.setInstructorId(existingCourse.getInstructorId().getUserid());
        courseDTO.setCategoryId(existingCourse.getCategoryId().getId());
        courseDTO.setCourseid(existingCourse.getCourseid());
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateCourse(
            @PathVariable Long id,
            @RequestPart("CourseDTO") String courseDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {

        Course existingCourse = courseService.getCourseById(id);
        CourseDTO dto = new ObjectMapper().readValue(courseDTO, CourseDTO.class);

        existingCourse.setCoursename(dto.getCoursename());
        existingCourse.setDescription(dto.getDescription());
        existingCourse.setLevel(dto.getLevel());
        existingCourse.setPrice(dto.getPrice());
        existingCourse.setVideoUrl(dto.getVideoUrl());
        existingCourse.setDuration(dto.getDuration());
        existingCourse.setCategoryId(courseCategoryService.getCategoryById(dto.getCategoryId()));
        existingCourse.setInstructorId(userService.getUserById(dto.getInstructorId()));

        if (file != null && !file.isEmpty()) {
            existingCourse.setCourseImage(file.getBytes());
        }

        courseService.updateCourse(existingCourse);


        Map<String, String> response = new HashMap<>();
        response.put("message", "Course updated successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
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



    @GetMapping("/without-quizzes")
    public ResponseEntity<List<Course>> getCoursesWithoutQuizzes() {
        List<Course> courses = courseRepository.findCoursesWithoutQuizzes();
        return ResponseEntity.ok(courses);
    }





}

