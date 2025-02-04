package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Mapperr.AnswerMapper;
import com.example.ProjectElearning.Model.Answer;
import com.example.ProjectElearning.Model.AnswerResponseDTO;
import com.example.ProjectElearning.Model.Question;
import com.example.ProjectElearning.Service.AnswerService;

import com.example.ProjectElearning.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secure/instructor/api/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        return new ResponseEntity<>(answerService.createAnswer(answer), HttpStatus.CREATED);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestion(questionId));
    }

}
