package com.example.ProjectElearning.Model;

public class CustomEnrollment {

   String username;
   String courseName;
   String status;

    public CustomEnrollment(String username, String courseName, String status) {
        this.username = username;
        this.courseName = courseName;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String payment) {
        status = payment;
    }
}
