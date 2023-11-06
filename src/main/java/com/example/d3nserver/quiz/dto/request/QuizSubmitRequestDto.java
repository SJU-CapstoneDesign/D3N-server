package com.example.d3nserver.quiz.dto.request;

import com.example.d3nserver.quiz.domain.SolvedQuiz;
import lombok.Data;

@Data
public class QuizSubmitRequestDto {
    private Long quizId;

    public QuizSubmitRequestDto(SolvedQuiz solvedQuiz){
        this.quizId = solvedQuiz.getQuizId();
    }
}
