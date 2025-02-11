package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.CourseFeedback;
import com.example.ProjectElearning.Model.Quiz;
import com.example.ProjectElearning.Model.QuizAttempt;
import com.example.ProjectElearning.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUser_Userid(Long userId);
    List<QuizAttempt> findByQuiz_QuizId(Long quizId);
    Optional<QuizAttempt> findByUserAndQuiz(User user, Quiz quiz);


    @Query("SELECT qa FROM QuizAttempt qa " +
            "JOIN qa.quiz q " +
            "JOIN q.course c " +
            "WHERE c.instructorId.userid = :instructorId")
    List<QuizAttempt> findQuizAttemptsByInstructor(@Param("instructorId") Long instructorId);



    @Query(nativeQuery = true,
            value="SELECT * FROM quiz_attempt  WHERE user_id = :userId")
    List<QuizAttempt> findByUserId(@Param("userId") Long userId);







}
