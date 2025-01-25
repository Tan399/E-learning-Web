package com.example.ProjectElearning.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseDTO {

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @NotBlank(message = "Course description is required")
    @Size(min = 3, max = 100, message = "Course Description  must be between 10 and 100 characters")
    private String description;
    private Long instructorId;
    private  Long categoryId;
    private String level;

    public CourseDTO(String courseName, String description, Long instructorId, Long categoryId,String level) {
        this.courseName = courseName;
        this.description = description;
        this.instructorId = instructorId;
        this.categoryId = categoryId;
        this.level=level;
    }

    public CourseDTO() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
