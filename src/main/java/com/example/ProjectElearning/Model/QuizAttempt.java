package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor

public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @JsonBackReference("quiz-attempt")
    @ManyToOne
    @JoinColumn(name = "quizId", nullable = false)
    private Quiz quiz;

    @JsonBackReference("user-attempt")
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private int score;

    public QuizAttempt(Quiz quiz, User user, int score) {
        this.quiz = quiz;
        this.user = user;
        this.score = score;
    }

    public QuizAttempt() {
    }


    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
