package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Mapperr.QuizAttemptMapper;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.CourseRepository;
import com.example.ProjectElearning.Repository.QuizAttemptRepository;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.QuizAttemptService;

import com.example.ProjectElearning.Service.QuizService;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz-attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping("/instructor/{instructorId}/course-quiz-results")
    public ResponseEntity<List<QuizAttemptDTO2>> getQuizAttemptsForInstructorCourses(@PathVariable Long instructorId) {
       List<QuizAttemptDTO2> temp=quizAttemptRepository.findQuizAttemptsByInstructor(instructorId).stream().map((quizAttempt -> {

           QuizAttemptDTO2 d=new QuizAttemptDTO2(quizAttempt.getQuiz().getCourse().getCoursename(),quizAttempt.getQuiz().getTitle(),quizAttempt.getUser().getFirstname()+" "+quizAttempt.getUser().getLastname(),quizAttempt.getScore());
           return  d;

       })).collect(Collectors.toList());

       return  ResponseEntity.ok( temp);
    }

    @GetMapping("/user/{userId}/course-quiz-results")
    public ResponseEntity<List<QuizAttemptDTO2>> getQuizAttemptsByUserId(@PathVariable Long userId) {
       List<QuizAttemptDTO2> temp=quizAttemptRepository.findByUserId(userId).stream().map((quizAttempt -> {
           System.out.println("course name "+quizAttempt.getQuiz().getCourse().getCoursename());
           QuizAttemptDTO2 d=new QuizAttemptDTO2(quizAttempt.getQuiz().getCourse().getCoursename(),quizAttempt.getQuiz().getTitle(),quizAttempt.getUser().getFirstname()+" "+quizAttempt.getUser().getLastname(),quizAttempt.getScore());
           return  d;

       })).collect(Collectors.toList());

       return  ResponseEntity.ok( temp);
    }


    @PostMapping("/attempt")
    public ResponseEntity<QuizAttempt> submitQuizAttempt(@RequestBody QuizAttemptDTO quizAttempt) {
        QuizAttempt attempt = quizAttemptService.attemptQuiz(quizAttempt);
        return new ResponseEntity<>(attempt, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizAttempt>> getAttemptsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByUser(userId));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizAttempt>> getAttemptsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByQuiz(quizId));
    }


}
