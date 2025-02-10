package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.EnrollmentRepository;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.EnrollmentService;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("secure/instructor/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/by-instructor/{instructorId}")
    public ResponseEntity<List<CustomEnrollment>> getEnrollmentsByInstructor(@PathVariable Long instructorId) {
        System.out.println("entered enrollment");
        List<CustomEnrollment>  updated =enrollmentRepository.findEnrollmentsByInstructorId(instructorId).stream().
                map((enrollment)->{
                    CustomEnrollment c=new CustomEnrollment(enrollment.getUser().getFirstname()+" "+enrollment.getUser().getLastname(),enrollment
                            .getCourse().getCoursename(),enrollment.getStatus());
                    System.out.println(enrollment.getUser().getUserid());
                    return c;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(updated);
    }


    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();


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

    @GetMapping("user/{id}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentByUserId(@PathVariable Long id) {

        List<EnrollmentResponseDTO> enrollment=enrollmentService.getEnrollmentByUserId(id).stream().map((enrollment1)->{
            EnrollmentResponseDTO dto=new EnrollmentResponseDTO(enrollment1.getId(),enrollment1.getStatus(),enrollment1.getUser().getUserid(),enrollment1.getCourse().getCourseid());
            return dto;
        }).collect(Collectors.toList());
        System.out.println(enrollment.toString());

        return ResponseEntity.ok(enrollment);
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