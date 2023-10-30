package com.example.d3nserver.quiz.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class SolvedQuiz {
    private Long quizId;
    private Integer selectedAnswer;
    private Integer quizAnswer;

    public SolvedQuiz(Long quizId, Integer selectedAnswer, Integer quizAnswer){
        this.quizId = quizId;
        this.selectedAnswer = selectedAnswer;
        this.quizAnswer = quizAnswer;
    }

    public boolean getQuizResult(){
        return selectedAnswer.intValue() == quizAnswer.intValue();
    }
}
