package com.example.ProjectElearning.Model;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseDTO {

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String coursename;

    @NotBlank(message = "Course description is required")
    @Size(min = 3, max = 100, message = "Course Description  must be between 10 and 100 characters")
    private String description;
    private Long instructorId;
    private  Long categoryId;
    private String level;
    private int price;
    private Long duration;
    private String videoUrl;


    public CourseDTO(String courseName, String description, Long instructorId, Long categoryId,String level) {
        this.coursename = courseName;
        this.description = description;
        this.instructorId = instructorId;
        this.categoryId = categoryId;
        this.level=level;
    }

    public CourseDTO() {
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


    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
