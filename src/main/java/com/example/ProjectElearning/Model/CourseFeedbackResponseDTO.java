package com.example.ProjectElearning.Model;

public class CourseFeedbackResponseDTO {
    private Long id;
    private String feedback;
    private Long courseId;
    private Long userId;

    public CourseFeedbackResponseDTO(Long id, String feedback, Long courseId, Long userId) {
        this.id = id;
        this.feedback = feedback;
        this.courseId = courseId;
        this.userId = userId;
    }

    public CourseFeedbackResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
