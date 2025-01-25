package com.example.ProjectElearning.Controller;



import com.example.ProjectElearning.Mapperr.QuizMapper;
import com.example.ProjectElearning.Model.Course;
import com.example.ProjectElearning.Model.Quiz;
import com.example.ProjectElearning.Model.QuizResponseDTO;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<QuizResponseDTO> response = quizzes.stream()
                .map(QuizMapper::toQuizResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(QuizMapper.toQuizResponseDTO(quiz));
    }


    @PostMapping("/{id}")
    public ResponseEntity<Quiz> createQuiz(@PathVariable Long id,@RequestBody Quiz quiz) {
        Course course=courseService.getCourseById(id);
        quiz.setCourse(course);
        Quiz savedQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(savedQuiz);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
