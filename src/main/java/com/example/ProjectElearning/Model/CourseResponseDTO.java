package com.example.ProjectElearning.Model;

public class CourseResponseDTO {
    private Long courseid;
    private String coursename;
    private String description;
    private String level;
    private Long instructorId;
    private Long categoryId;

    public CourseResponseDTO(Long courseid, String coursename, String description, String level, Long instructorId, Long categoryId) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.description = description;
        this.level = level;
        this.instructorId = instructorId;
        this.categoryId = categoryId;
    }

    public CourseResponseDTO() {
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
}
