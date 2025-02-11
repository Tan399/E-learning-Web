package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuiz_QuizId(Long quizId);

}
