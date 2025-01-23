package com.example.ProjectElearning.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String gender;
    @Enumerated(value = EnumType.STRING)
    private Role userTypee;

    @ManyToOne
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    public User(Long userid, String firstname, String lastname, String email, String password, String gender, Role userTypee, UserType userType) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.userTypee = userTypee;
        this.userType = userType;
//        this.enrollments = enrollments;
//        this.payments = payments;
    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Role getUserTypee() {
        return userTypee;
    }

    public void setUserTypee(Role userTypee) {
        this.userTypee = userTypee;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
