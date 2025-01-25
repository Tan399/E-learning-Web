package com.example.ProjectElearning.Model;

import java.util.List;

public class QuestionResponseDTO {
    private Long questionId;
    private String questionText;
    private List<AnswerResponseDTO> answers;

    public QuestionResponseDTO(Long questionId, String questionText, List<AnswerResponseDTO> answers) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answers = answers;
    }

    public QuestionResponseDTO() {}

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerResponseDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResponseDTO> answers) {
        this.answers = answers;
    }
}
