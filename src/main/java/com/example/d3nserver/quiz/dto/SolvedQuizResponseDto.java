package com.example.d3nserver.quiz.dto;

import com.example.d3nserver.quiz.domain.SolvedQuiz;
import lombok.Data;

@Data
public class SolvedQuizResponseDto {
    private Long quizId;

    public SolvedQuizResponseDto(SolvedQuiz solvedQuiz){
        this.quizId = solvedQuiz.getQuizId();
    }
}
