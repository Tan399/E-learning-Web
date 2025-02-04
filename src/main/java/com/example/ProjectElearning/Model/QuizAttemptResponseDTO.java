package com.example.ProjectElearning.Model;
public class QuizAttemptResponseDTO {
    private Long attemptId;
    private Long userId;
    private Long quizId;
    private int score;


    public QuizAttemptResponseDTO(Long attemptId, Long userId, Long quizId, int score) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;

    }

    public QuizAttemptResponseDTO() {}

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
