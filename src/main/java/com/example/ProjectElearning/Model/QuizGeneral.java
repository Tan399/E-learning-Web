package com.example.ProjectElearning.Model;

import java.util.List;

public class QuizGeneral {
    private String title;
    private String description;
  private Long  courseId;
  private List<QuestionArray> questions;

    public QuizGeneral() {
    }

    public QuizGeneral(String title, String description, Long courseId) {
        this.title = title;
        this.description = description;
        this.courseId = courseId;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    public List<QuestionArray> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionArray> questions) {
        this.questions = questions;
    }
}
