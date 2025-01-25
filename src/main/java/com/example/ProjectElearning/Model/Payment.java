package com.example.ProjectElearning.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;


    @JsonBackReference("course-payment")
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;


    @JsonBackReference("user-payment")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Payment() {
    }

    public Payment(Long id, Double amount, Date paymentDate, User user) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
