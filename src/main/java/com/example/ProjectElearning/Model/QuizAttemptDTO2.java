package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizAttemptDTO2 {

   String courseName;
       String     quizTitle;
   String studentName;
        int    score;

    public QuizAttemptDTO2(String courseName, String quizTitle, String studentName, int score) {
        this.courseName = courseName;
        this.quizTitle = quizTitle;
        this.studentName = studentName;
        this.score = score;
    }

    public QuizAttemptDTO2() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}