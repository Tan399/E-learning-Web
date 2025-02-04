package com.example.ProjectElearning.Model;

import java.util.Date;

public class PaymentResponseDTO {
    private Long id;
    private Double amount;
    private Date paymentDate;
    private Long courseId;
    private Long userId;

    public PaymentResponseDTO(Long id, Double amount, Date paymentDate, Long courseId, Long userId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.courseId = courseId;
        this.userId = userId;
    }

    public PaymentResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
