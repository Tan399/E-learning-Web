package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @NotBlank(message = "Quiz title is required")
    @Size(min = 3, max = 50, message = "Quiz title must be between 3 and 50 characters")
    private String title;

    @Size(max = 200, message = "Description cannot exceed v characters")
    private String description;

//    @JsonBackReference("course-quiz")
//    @ManyToOne
//    @JoinColumn(name = "courseId", nullable = false)
//    private Course course;

    @JsonBackReference("course-quiz")
    @ManyToOne
    @JoinColumn(name = "course", nullable = false, unique = true)
    private Course course;

    @JsonManagedReference("quiz-question")
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @JsonManagedReference("quiz-attempt")
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<QuizAttempt> quizAttempts;



//    private List<QuizAttemptDTO2> quizAttemptsDTO2;
//
//    public void setQuizAttemptsDTO2(List<QuizAttemptDTO2> quizAttemptsDTO2) {
//        this.quizAttemptsDTO2 = quizAttemptsDTO2;
//    }
//
//    public List<QuizAttemptDTO2> getQuizAttemptsDTO2() {
//        return quizAttempts.stream()
//                .map(quizAttempt -> new QuizAttemptDTO2(quizAttempt))
//                .collect(Collectors.toList());
//    }





    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }

    public void setQuizAttempts(List<QuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }

//    public Course getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Course courseId) {
//        this.courseId = courseId;
//    }
}
