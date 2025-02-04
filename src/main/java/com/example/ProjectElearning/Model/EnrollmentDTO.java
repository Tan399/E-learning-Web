package com.example.ProjectElearning.Model;

public class EnrollmentDTO {


    private String status;

    private Long userId;

    private Long courseId;

    public EnrollmentDTO( String status, Long userId, Long courseId) {

        this.status = status;
        this.userId = userId;
        this.courseId = courseId;
    }

    public EnrollmentDTO() {
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
