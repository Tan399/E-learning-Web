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
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;


    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        List<QuestionResponseDTO> response = questions.stream()
                .map(QuestionMapper::toQuestionResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(QuestionMapper.toQuestionResponseDTO(question));
    }


    @PostMapping("/{id}")
    public ResponseEntity<Question> createQuestion(@PathVariable Long id,@RequestBody Question question) {
        Quiz quiz=quizService.getQuizById(id);
        question.setQuiz(quiz);
        Question savedQuestion = questionService.createQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
