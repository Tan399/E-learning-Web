package com.example.ProjectElearning.Model;

import java.util.Date;

public class CoursePayment {

  String  courseName;
     String  studentName;
    Double paymentAmount;
     Date paymentDate;

    public CoursePayment(String courseName, String studentName, Double paymentAmount, Date paymentDate) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
