package com.example.ProjectElearning.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String firstname;
    private String lastname;


    private String email;
    private String password;
    private String gender;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Lob
    private byte[] image;
//    @Enumerated(value = EnumType.STRING)
//    private Role userTypee;


    @JsonBackReference("user-userType")
    @ManyToOne
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    @JsonManagedReference("user-enrollments")
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;



    @JsonManagedReference("user-payment")
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment> payments;

    @JsonManagedReference("user-attempt")
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<QuizAttempt> quizAttempts;


    @JsonManagedReference("user-courses")
    @OneToMany(mappedBy = "instructorId",cascade = CascadeType.ALL)
    private List<Course> courses;


    @JsonManagedReference("user-course-feedback")
    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<CourseFeedback> courseFeedbacks;



    public User(Long userid, String firstname, String lastname, String email, String password, String gender) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
//        this.userTypee = userTypee;
//        this.userType = userType;
//        this.enrollments = enrollments;
//        this.payments = payments;
    }

    public User() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userType.getType()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

//    public Role getUserTypee() {
//        return userTypee;
//    }
//
//    public void setUserTypee(Role userTypee) {
//        this.userTypee = userTypee;
//    }

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<CourseFeedback> getCourseFeedbacks() {
        return courseFeedbacks;
    }

    public void setCourseFeedbacks(List<CourseFeedback> courseFeedbacks) {
        this.courseFeedbacks = courseFeedbacks;
    }

    public List<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }

    public void setQuizAttempts(List<QuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }
}
