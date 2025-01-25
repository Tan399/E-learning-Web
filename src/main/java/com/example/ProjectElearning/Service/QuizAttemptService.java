package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Model.QuizAttempt;
import com.example.ProjectElearning.Repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public List<QuizAttempt> getAllAttempts() {
        return quizAttemptRepository.findAll();
    }

    public QuizAttempt getAttemptById(Long attemptId) {
        return quizAttemptRepository.findById(attemptId).orElse(null);
    }

    public List<QuizAttempt> getAttemptsByUserId(Long userId) {
        return quizAttemptRepository.findByUserId(userId);
    }

    public List<QuizAttempt> getAttemptsByQuizId(Long quizId) {
        return quizAttemptRepository.findByQuizId(quizId);
    }

    public QuizAttempt saveQuizAttempt(QuizAttempt attempt) {
        return quizAttemptRepository.save(attempt);
    }

    public void deleteQuizAttempt(Long attemptId) {
        quizAttemptRepository.deleteById(attemptId);
    }
}
