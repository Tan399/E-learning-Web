package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.CourseFeedback;
import com.example.ProjectElearning.Model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    @Query("SELECT at FROM QuizAttempt at WHERE at.user.id = :userid")
    List<QuizAttempt> findByUserId(@Param("userid") Long userid);


    @Query("SELECT at FROM QuizAttempt at WHERE at.quizAttempt.quizId = :quizid")
    List<QuizAttempt> findByQuizId(@Param("quizid") Long quizid);



}
