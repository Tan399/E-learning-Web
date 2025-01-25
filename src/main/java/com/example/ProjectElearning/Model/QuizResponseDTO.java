package com.example.ProjectElearning.Model;
import java.util.List;

public class QuizResponseDTO {
    private Long quizId;
    private String title;
    private String description;
    private Long courseId;
    private List<QuestionResponseDTO> questions;

    public QuizResponseDTO(Long quizId, String title, String description, Long courseId, List<QuestionResponseDTO> questions) {
        this.quizId = quizId;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.questions = questions;
    }

    public QuizResponseDTO() {}

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponseDTO> questions) {
        this.questions = questions;
    }
}
