package com.example.d3nserver.quiz.dto;

import com.example.d3nserver.quiz.domain.Quiz;
import lombok.Data;

import java.util.List;
@Data
public class QuizDTO {

    private String question;
    private List<String> choiceList;
    private Integer answer;
    private String reason;

    public QuizDTO(Quiz quiz){
        this.question = quiz.getQuestion();
        this.choiceList = quiz.getChoiceList();
        this.answer = quiz.getAnswer();
        this.reason = quiz.getReason();
    }
}
