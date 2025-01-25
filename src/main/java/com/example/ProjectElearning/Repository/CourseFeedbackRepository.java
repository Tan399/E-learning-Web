package com.example.ProjectElearning.Repository;



import com.example.ProjectElearning.Model.User;
import org.springframework.data.jpa.repository.Query;
import com.example.ProjectElearning.Model.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {
    CourseFeedback findByFeedBackId(Long feedBackId);


    @Query("SELECT cf FROM CourseFeedback cf WHERE cf.courseId.courseid = :courseid")
    List<CourseFeedback> findByCourseId(@Param("courseid") Long courseid);




    @Query("SELECT cf FROM CourseFeedback cf WHERE cf.userId.userid = :userid")
    List<CourseFeedback> findByUserId (@Param("userid") Long userid);


}

