package com.example.ProjectElearning.Model;



import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "courseFeedback")
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedBackId;

    @Column(nullable = false, length = 100)
    private String feedback;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Long userId;

    public CourseFeedback(Long feedBackId, String feedback, Long courseId, Long userId) {
        this.feedBackId = feedBackId;
        this.feedback = feedback;
        this.courseId = courseId;
        this.userId = userId;
    }

    public CourseFeedback() {
    }

    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
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

