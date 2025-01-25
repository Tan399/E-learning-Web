package com.example.ProjectElearning.Model;

public class EnrollmentResponseDTO {
    private Long id;
    private String status;
    private Long userId;
    private Long courseId;


    public EnrollmentResponseDTO() {
    }

    public EnrollmentResponseDTO(Long id, String status, Long userId, Long courseId) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.courseId = courseId;
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
