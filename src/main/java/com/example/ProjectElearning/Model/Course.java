package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "courseid")
    private Long courseid;
    private String coursename;
    private String description;
    private String level;
    private int price;
    private String videoUrl;
    @Lob
    private byte[] courseImage;
    private Long duration;



    @JsonBackReference("user-courses")
    @ManyToOne
    @JoinColumn(name = "instructorId")
    private User instructorId;


    @JsonManagedReference("course-feedback")
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private List<CourseFeedback> courseFeedbackList;

    @JsonManagedReference("course-enrollments")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @JsonManagedReference("course-payment")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Payment> payments;


    @JsonManagedReference("course-quiz")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;


//    @JsonManagedReference("course-quiz")
//    @OneToOne(mappedBy = "courseId", cascade = CascadeType.ALL)
//    private Quiz quiz;



    @JsonBackReference("course-category")
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private CourseCategory categoryId;




    public Course() {
    }

    public Course(Long courseid, String coursename, String description, String level) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.description = description;
        this.level = level;


    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public User getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(User instructorId) {
        this.instructorId = instructorId;
    }

    public CourseCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CourseCategory categoryId) {
        this.categoryId = categoryId;
    }

    public List<CourseFeedback> getCourseFeedbackList() {
        return courseFeedbackList;
    }

    public void setCourseFeedbackList(List<CourseFeedback> courseFeedbackList) {
        this.courseFeedbackList = courseFeedbackList;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

//    public List<Quiz> getQuizzes() {
//        return quizzes;
//    }
//
//    public void setQuizzes(List<Quiz> quizzes) {
//        this.quizzes = quizzes;
//    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public byte[] getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(byte[] courseImage) {
        this.courseImage = courseImage;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

//    public Quiz getQuiz() {
//        return quiz;
//    }
//
//    public void setQuiz(Quiz quiz) {
//        this.quiz = quiz;
//    }
}
