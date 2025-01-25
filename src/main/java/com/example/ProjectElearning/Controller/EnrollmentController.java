package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.EnrollmentService;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        // Map Enrollment to EnrollmentDTO
        List<EnrollmentResponseDTO> enrollmentDTOs = enrollments.stream().map(enrollment -> {
            EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
            dto.setId(enrollment.getId());
            dto.setStatus(enrollment.getStatus());
            dto.setUserId(enrollment.getUser().getUserid());
            dto.setCourseId(enrollment.getCourse().getCourseid());
            return dto;
        }).toList();

        return ResponseEntity.ok(enrollmentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id) {

        Enrollment enrollment=enrollmentService.getEnrollmentById(id);
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setStatus(enrollment.getStatus());
        dto.setUserId(enrollment.getUser().getUserid());
        dto.setCourseId(enrollment.getCourse().getCourseid());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment=new Enrollment();
        enrollment.setStatus(enrollmentDTO.getStatus());
        User user=userService.getUserById(enrollmentDTO.getUserId());
        Course course=courseService.getCourseById(enrollmentDTO.getCourseId());
        enrollment.setCourse(course);
        enrollment.setUser(user);
        user.getEnrollments().add(enrollment);




        return new ResponseEntity<Enrollment>( enrollmentService.createEnrollment(enrollment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}