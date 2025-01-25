package com.example.ProjectElearning.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;


    @JsonBackReference("user-enrollments")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    @JsonBackReference("course-enrollments")
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public Enrollment(Long id, String status, User user, Course course) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.course = course;
    }

    public Enrollment() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}