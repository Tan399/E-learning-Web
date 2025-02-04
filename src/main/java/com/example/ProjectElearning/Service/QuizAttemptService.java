package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Exception.QuizAlreadyAttemptedException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.CourseRepository;
import com.example.ProjectElearning.Repository.QuizAttemptRepository;
import com.example.ProjectElearning.Repository.QuizRepository;
import com.example.ProjectElearning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAttemptService {




    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    public QuizAttempt attemptQuiz(QuizAttemptDTO quizAttempt) {
        User user = userRepository.findByUserid(quizAttempt.getUserId());
        Quiz quiz = quizRepository.findByQuizId(quizAttempt.getQuizId());

        Optional<QuizAttempt> existingAttempt = quizAttemptRepository.findByUserAndQuiz(user, quiz);
        if (existingAttempt.isPresent()) {
            throw new QuizAlreadyAttemptedException("User has already attempted this quiz.");
        }

        QuizAttempt quizAttempt1 = new QuizAttempt(quiz, user, quizAttempt.getScore());
        return quizAttemptRepository.save(quizAttempt1);
    }


    public List<QuizAttempt> getAttemptsByUser(Long userId) {
        return quizAttemptRepository.findByUser_Userid(userId);
    }

    public List<QuizAttempt> getAttemptsByQuiz(Long quizId) {
        return quizAttemptRepository.findByQuiz_QuizId(quizId);
    }

//    @Autowired
//    private QuizAttemptRepository quizAttemptRepository;
//
//    public List<QuizAttempt> getAllAttempts() {
//        return quizAttemptRepository.findAll();
//    }
//
//    public QuizAttempt getAttemptById(Long attemptId) {
//        return quizAttemptRepository.findById(attemptId).orElse(null);
//    }
//
//    public List<QuizAttempt> getAttemptsByUserId(Long userId) {
//        return quizAttemptRepository.findByUserId(userId);
//    }
//
//    public List<QuizAttempt> getAttemptsByQuizId(Long quizId) {
//        return quizAttemptRepository.findByQuizId(quizId);
//    }
//
//    public QuizAttempt saveQuizAttempt(QuizAttempt attempt) {
//        return quizAttemptRepository.save(attempt);
//    }
//
//    public void deleteQuizAttempt(Long attemptId) {
//        quizAttemptRepository.deleteById(attemptId);
//    }
//}
}
