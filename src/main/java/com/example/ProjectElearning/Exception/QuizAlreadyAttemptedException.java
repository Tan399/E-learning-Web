package com.example.ProjectElearning.Exception;

public class QuizAlreadyAttemptedException extends RuntimeException {
    public QuizAlreadyAttemptedException(String message) {
        super(message);
    }
}