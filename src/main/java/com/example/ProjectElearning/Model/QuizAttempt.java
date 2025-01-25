package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @JsonBackReference("quiz-attempt")
    @ManyToOne
    @JoinColumn(name = "quizId", nullable = false)
    private Quiz quizAttempt;

    @JsonBackReference("user-attempt")
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private int score;


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



    public Quiz getQuizAttempt() {
        return quizAttempt;
    }

    public void setQuizAttempt(Quiz quizAttempt) {
        this.quizAttempt = quizAttempt;
    }
}
