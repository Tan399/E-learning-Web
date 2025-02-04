package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Mapperr.QuestionMapper;
import com.example.ProjectElearning.Model.Question;
import com.example.ProjectElearning.Model.QuestionResponseDTO;
import com.example.ProjectElearning.Model.Quiz;
import com.example.ProjectElearning.Service.QuestionService;

import com.example.ProjectElearning.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secure/instructor/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }

}
