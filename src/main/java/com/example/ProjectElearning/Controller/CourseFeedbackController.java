package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Model.CourseFeedback;
import com.example.ProjectElearning.Service.CourseFeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coursefeedback")
public class CourseFeedbackController {

    private final CourseFeedbackService courseFeedbackService;

    public CourseFeedbackController(CourseFeedbackService courseFeedbackService) {
        this.courseFeedbackService = courseFeedbackService;
    }


    @GetMapping
    public ResponseEntity<List<CourseFeedback>> getAllFeedbacks() {
        List<CourseFeedback> feedbacks = courseFeedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseFeedback> getFeedbackById(@PathVariable Long id) {
        CourseFeedback feedback = courseFeedbackService.getFeedbackById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseFeedback>> getFeedbacksByCourseId(@PathVariable Long courseId) {
        List<CourseFeedback> feedbacks = courseFeedbackService.getFeedbacksByCourseId(courseId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseFeedback>> getFeedbacksByUserId(@PathVariable Long userId) {
        List<CourseFeedback> feedbacks = courseFeedbackService.getFeedbacksByUserId(userId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CourseFeedback> createFeedback(@RequestBody CourseFeedback courseFeedback) {
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
