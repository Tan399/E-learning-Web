package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Model.Answer;
import com.example.ProjectElearning.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestion_QuestionId(questionId);
    }
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    public List<Answer> getAllAnswers() {
//        return answerRepository.findAll();
//    }
//
//    public Answer getAnswerById(Long id) {
//        return answerRepository.findById(id).orElse(null);
//    }
//
//    public Answer createAnswer(Answer answer) {
//        return answerRepository.save(answer);
//    }
//
//    public void deleteAnswer(Long id) {
//        answerRepository.deleteById(id);
//    }
}
