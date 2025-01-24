package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class PaymentDTO {
    private Double amount;



    @Temporal(TemporalType.DATE)
    private Date paymentDate;



    private Long userId;

    public PaymentDTO() {
    }

    public PaymentDTO(Double amount, Date paymentDate, Long userId) {

        this.amount = amount;
        this.paymentDate = paymentDate;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
