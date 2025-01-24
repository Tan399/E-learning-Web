package com.example.ProjectElearning.Model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "courseFeedback")
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedBackId;

    @Column(nullable = false, length = 100)
    private String feedback;


    @JsonBackReference("course-feedback")
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course courseId;


    @JsonBackReference("user-course-feedback")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public CourseFeedback(Long feedBackId, String feedback) {
        this.feedBackId = feedBackId;
        this.feedback = feedback;


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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }


//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

