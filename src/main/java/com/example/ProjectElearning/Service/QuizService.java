package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.AnswerRepository;
import com.example.ProjectElearning.Repository.CourseRepository;
import com.example.ProjectElearning.Repository.QuestionRepository;
import com.example.ProjectElearning.Repository.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizService(QuizRepository quizRepository, CourseRepository courseRepository,
                       QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.courseRepository = courseRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Quiz createQuiz(QuizDTO quizDTO) {
        try {
            Course course = courseRepository.findByCourseid(quizDTO.getCourseid());
            if (course == null) {
                throw new IllegalArgumentException("Course not found with ID: " + quizDTO.getCourseid());
            }

            Quiz quiz = new Quiz();
            quiz.setCourse(course);
            quiz.setTitle(quizDTO.getTitle());
            quiz.setDescription(quizDTO.getDescription());
            quiz.setQuestions(new ArrayList<>());

            quiz = quizRepository.save(quiz);

            List<Question> savedQuestions = new ArrayList<>();
            for (Question question : quizDTO.getQuestions()) {
                question.setQuiz(quiz);

                if (question.getAnswers() == null) {
                    question.setAnswers(new ArrayList<>());
                }


                question = questionRepository.save(question);
                savedQuestions.add(question);
            }


            for (Question question : savedQuestions) {
                List<Answer> filteredAnswers = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    Optional<Answer> existingAnswer = answerRepository.findByAnswerTextAndQuestion(answer.getAnswerText(), question);
                    if (existingAnswer.isEmpty()) {
                        answer.setQuestion(question);
                        filteredAnswers.add(answer);
                    }
                }
                answerRepository.saveAll(filteredAnswers);
            }

            return quizRepository.save(quiz);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating quiz: " + e.getMessage());
        }
    }


    public Quiz getQuizzesByCourse(Long courseId) {

        try {
            Quiz quiz = quizRepository.findByCourse_Courseid(courseId).get(0);
            return quiz;
        } catch (Exception e) {
            throw new ResourceNotFoundException("No quiz Available for the Course");
        }


    }
}
