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
@RequestMapping("/api/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<AnswerResponseDTO>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        List<AnswerResponseDTO> response = answers.stream()
                .map(AnswerMapper::toAnswerResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(AnswerMapper.toAnswerResponseDTO(answer));
    }


    @PostMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> createAnswer(@PathVariable Long id,@RequestBody Answer answer) {
        Question question=questionService.getQuestionById(id);
        answer.setQuestion(question);
        Answer savedAnswer = answerService.createAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnswerMapper.toAnswerResponseDTO(savedAnswer));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
