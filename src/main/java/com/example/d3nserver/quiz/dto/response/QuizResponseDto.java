package com.example.d3nserver.quiz.dto.response;

import com.example.d3nserver.quiz.domain.Quiz;
import lombok.Data;

import java.util.List;
@Data
public class QuizResponseDto {

    private Long id;
    private String question;
    private List<String> choiceList;
    private Integer answer;
    private String reason;
    private Integer selectedAnswer;

    public QuizResponseDto(Quiz quiz){
        this.id = quiz.getId();
        this.question = quiz.getQuestion();
        this.choiceList = quiz.getChoiceList();
        this.answer = quiz.getAnswer();
        this.reason = quiz.getReason();
    }
}
