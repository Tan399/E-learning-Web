package com.example.ProjectElearning.Model;

import jakarta.persistence.*;

@Entity
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String material;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public CourseMaterial(Long id, String material) {
        this.id = id;
        this.material = material;
    }

    public CourseMaterial() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
