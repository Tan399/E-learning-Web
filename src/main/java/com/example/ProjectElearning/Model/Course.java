package com.example.ProjectElearning.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String coursename;
    private String description;
    private String level;

    @NotNull(message = "Instructor ID is required")
    private Long instructorid;

    @NotBlank(message = "Category is required")
    private String category;

    public Course() {
    }

    public Course(Long courseid, String coursename, String description, String level, Long instructorid, String category) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.description = description;
        this.level = level;
        this.instructorid = instructorid;
        this.category = category;
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

    public Long getInstructorid() {
        return instructorid;
    }

    public void setInstructorid(Long instructorid) {
        this.instructorid = instructorid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
