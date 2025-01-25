package com.example.ProjectElearning.Model;
public class AnswerResponseDTO {
    private Long answerId;
    private String answerText;

    public AnswerResponseDTO(Long answerId, String answerText) {
        this.answerId = answerId;
        this.answerText = answerText;
    }

    public AnswerResponseDTO() {}

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
