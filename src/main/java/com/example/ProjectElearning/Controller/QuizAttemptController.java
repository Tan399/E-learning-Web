package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Mapperr.QuizAttemptMapper;
import com.example.ProjectElearning.Model.QuizAttempt;
import com.example.ProjectElearning.Model.QuizAttemptResponseDTO;
import com.example.ProjectElearning.Service.QuizAttemptService;

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


    @GetMapping
    public ResponseEntity<List<QuizAttemptResponseDTO>> getAllQuizAttempts() {
        List<QuizAttempt> attempts = quizAttemptService.getAllAttempts();
        List<QuizAttemptResponseDTO> response = attempts.stream()
                .map(QuizAttemptMapper::toQuizAttemptResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuizAttemptResponseDTO> getQuizAttemptById(@PathVariable Long id) {
        QuizAttempt attempt = quizAttemptService.getAttemptById(id);
        if (attempt == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(QuizAttemptMapper.toQuizAttemptResponseDTO(attempt));
    }


    @PostMapping
    public ResponseEntity<QuizAttemptResponseDTO> createQuizAttempt(@RequestBody QuizAttempt quizAttempt) {
        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(quizAttempt);
        return ResponseEntity.status(HttpStatus.CREATED).body(QuizAttemptMapper.toQuizAttemptResponseDTO(savedAttempt));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizAttempt(@PathVariable Long id) {
        quizAttemptService.deleteQuizAttempt(id);
        return ResponseEntity.noContent().build();
    }
}
