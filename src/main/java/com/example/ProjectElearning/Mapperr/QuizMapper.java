package com.example.ProjectElearning.Mapperr;

import com.example.ProjectElearning.Model.Quiz;
import com.example.ProjectElearning.Model.QuizResponseDTO;

import java.util.stream.Collectors;

public class QuizMapper {
    public static QuizResponseDTO toQuizResponseDTO(Quiz quiz) {
        return new QuizResponseDTO(
                quiz.getQuizId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCourse().getCourseid(),
                quiz.getQuestions().stream()
                        .map(QuestionMapper::toQuestionResponseDTO)
                        .collect(Collectors.toList())
        );
    }
}
