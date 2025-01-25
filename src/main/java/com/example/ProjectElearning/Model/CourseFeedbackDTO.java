package com.example.ProjectElearning.Model;

public class CourseFeedbackDTO {
    private String feedback;
    private Long courseId;
    private Long userId;

    public CourseFeedbackDTO(String feedback, Long courseId, Long userId) {
        this.feedback = feedback;
        this.courseId = courseId;
        this.userId = userId;
    }

    public CourseFeedbackDTO() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
