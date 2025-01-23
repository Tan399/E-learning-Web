package com.example.ProjectElearning.Repository;




import com.example.ProjectElearning.Model.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {
    CourseFeedback findByFeedBackId(Long feedBackId);
    List<CourseFeedback> findByCourseId(Long courseId);
    List<CourseFeedback> findByUserId(Long userId);
}

