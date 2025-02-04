package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.Answer;
import com.example.ProjectElearning.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion_QuestionId(Long questionId);

    Optional<Answer> findByAnswerTextAndQuestion(String answerText, Question question);
}
