package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Service.CourseFeedbackService;
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
@RequestMapping("/api/coursefeedback")
public class CourseFeedbackController {

    @Autowired
    private  CourseFeedbackService courseFeedbackService;
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;





    @GetMapping
    public ResponseEntity<List<CourseFeedbackResponseDTO>> getAllFeedbacks() {
        List<CourseFeedbackResponseDTO> feedbacks = courseFeedbackService.getAllFeedbacks().stream()
                .map((feedback) -> {
                    CourseFeedbackResponseDTO dto = new CourseFeedbackResponseDTO();
                    dto.setFeedback(feedback.getFeedback());
                    dto.setCourseId(feedback.getCourseId().getCourseid());
                    dto.setUserId(feedback.getUserId().getUserid());
                    dto.setId(feedback.getFeedBackId());
                    return dto;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseFeedbackResponseDTO> getFeedbackById(@PathVariable Long id) {
        CourseFeedback feedback = courseFeedbackService.getFeedbackById(id);
        CourseFeedbackResponseDTO dto=new CourseFeedbackResponseDTO();
        dto.setFeedback(feedback.getFeedback());
        dto.setCourseId(feedback.getCourseId().getCourseid());
        dto.setUserId(feedback.getUserId().getUserid());
        dto.setId(feedback.getFeedBackId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByCourseId(@PathVariable Long courseId) {
        List<Feedback> feedbacks = courseFeedbackService.getFeedbacksByCourseId(courseId).stream()
                .map((feedback)->{
                    Feedback dto=new Feedback();
                    dto.setFeedback(feedback.getFeedback());

                    dto.setName(feedback.getUserId().getFirstname()+" "+feedback.getUserId().getLastname());
                    System.out.println(dto.getFeedback());
                    System.out.println(dto.getName());
                    return dto;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseFeedbackResponseDTO>> getFeedbacksByUserId(@PathVariable Long userId) {
        List<CourseFeedbackResponseDTO> feedbacks = courseFeedbackService.getFeedbacksByUserId(userId).stream()
                .map((feedback)->{
                    CourseFeedbackResponseDTO dto=new CourseFeedbackResponseDTO();
                    dto.setFeedback(feedback.getFeedback());
                    dto.setCourseId(feedback.getCourseId().getCourseid());
                    dto.setUserId(feedback.getUserId().getUserid());
                    dto.setId(feedback.getFeedBackId());
                    return dto;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CourseFeedback> createFeedback( @RequestBody CourseFeedbackDTO courseFeedbackDTO) {
        CourseFeedback courseFeedback=new CourseFeedback();
        courseFeedback.setFeedback(courseFeedbackDTO.getFeedback());
        User user=userService.getUserById(courseFeedbackDTO.getUserId());
        Course course=courseService.getCourseById(courseFeedbackDTO.getCourseId());
        courseFeedback.setCourseId(course);
        courseFeedback.setUserId(user);
        user.getCourseFeedbacks().add(courseFeedback);
        course.getCourseFeedbackList().add(courseFeedback);
        CourseFeedback createdFeedback = courseFeedbackService.createFeedback(courseFeedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseFeedback> updateFeedback(@PathVariable Long id, @RequestBody CourseFeedback updatedFeedback) {
        CourseFeedback feedback = courseFeedbackService.updateFeedback(id, updatedFeedback);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        courseFeedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
