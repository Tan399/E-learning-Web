package com.example.ProjectElearning.Model;
import java.util.List;

public class QuestionDTO {

    private String questionText;
    private  List<AnswerDTO> answer;



    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


    public List<AnswerDTO> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AnswerDTO> answer) {
        this.answer = answer;
    }
}
