package com.example.ProjectElearning.Controller;



import com.example.ProjectElearning.Mapperr.QuizMapper;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private CourseService courseService;




    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quiz) {
        if (quiz.getCourseid() == null) {
            throw new IllegalArgumentException("Course must be provided.");
        }
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Quiz> getQuizzesByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(quizService.getQuizzesByCourse(courseId));
    }







}
