package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.Course;
import com.example.ProjectElearning.Model.Enrollment;
import com.example.ProjectElearning.Model.EnrollmentDTO;
import com.example.ProjectElearning.Model.User;
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
    public List<Enrollment> getAllEnrollments() {

        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
        EnrollmentDTO enrollmentDTO=new EnrollmentDTO();
        Enrollment enrollment=enrollmentService.getEnrollmentById(id);
        enrollmentDTO.setCourseId(enrollment.getCourse().getCourseid());
        enrollmentDTO.setUserId(enrollment.getUser().getUserid());
        enrollmentDTO.setStatus(enrollment.getStatus());
        return ResponseEntity.ok(enrollmentDTO);
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
        userService.updateUser(user);

        return new ResponseEntity<Enrollment>( enrollmentService.createEnrollment(enrollment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}