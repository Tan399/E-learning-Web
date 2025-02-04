package com.example.ProjectElearning.Model;

public class Feedback {
    String name;
    String feedback;

    public Feedback(String name, String feedback) {
        this.name = name;
        this.feedback = feedback;
    }

    public Feedback() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
