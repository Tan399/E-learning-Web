package com.example.ProjectElearning.Model;

import java.util.List;

public class QuestionResponseDTO {
    private Long questionId;
    private String questionText;
    private Long answer;

    public QuestionResponseDTO(Long questionId, String questionText, Long answer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answer = answer;
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


    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }
}
