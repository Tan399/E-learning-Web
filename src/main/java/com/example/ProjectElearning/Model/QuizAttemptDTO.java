package com.example.ProjectElearning.Model;

public class QuizAttemptDTO {


    private Long userId;
    private Long quizId;
    private int score;



    public QuizAttemptDTO(Long userId, Long quizId, int score) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
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
