package com.example.ProjectElearning.Model;

public class QuestionArray {
    private String questionText;
    private String answerText;

    public QuestionArray(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public QuestionArray() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
