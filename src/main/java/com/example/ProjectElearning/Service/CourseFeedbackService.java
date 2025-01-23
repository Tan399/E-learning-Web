package com.example.ProjectElearning.Service;




import com.example.ProjectElearning.Model.CourseFeedback;
import com.example.ProjectElearning.Repository.CourseFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseFeedbackService {


    @Autowired
    private final CourseFeedbackRepository courseFeedbackRepository;

    public CourseFeedbackService(CourseFeedbackRepository courseFeedbackRepository) {
        this.courseFeedbackRepository = courseFeedbackRepository;
    }


    public List<CourseFeedback> getAllFeedbacks() {
        return courseFeedbackRepository.findAll();
    }


    public CourseFeedback getFeedbackById(Long id) {
        return courseFeedbackRepository.findByFeedBackId(id);
    }


    public List<CourseFeedback> getFeedbacksByCourseId(Long courseId) {
        return courseFeedbackRepository.findByCourseId(courseId);
    }


    public List<CourseFeedback> getFeedbacksByUserId(Long userId) {
        return courseFeedbackRepository.findByUserId(userId);
    }


    public CourseFeedback createFeedback(CourseFeedback courseFeedback) {
        return courseFeedbackRepository.save(courseFeedback);
    }


    public CourseFeedback updateFeedback(Long id, CourseFeedback updatedFeedback) {
        CourseFeedback existingFeedback = getFeedbackById(id);
        existingFeedback.setFeedback(updatedFeedback.getFeedback());
        return courseFeedbackRepository.save(existingFeedback);
    }


    public void deleteFeedback(Long id) {
        CourseFeedback feedback = getFeedbackById(id);
        courseFeedbackRepository.delete(feedback);
    }
}

