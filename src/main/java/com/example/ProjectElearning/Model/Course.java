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
    private Long courseid;




    private String coursename;
    private String description;
    private String level;



    @JsonBackReference("user-courses")
    @ManyToOne
    @JoinColumn(name = "instructorId")
    private User instructorId;


    @JsonManagedReference("course-feedback")
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private List<CourseFeedback> courseFeedbackList;



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
}
