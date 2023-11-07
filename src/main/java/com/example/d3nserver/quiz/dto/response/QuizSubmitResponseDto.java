package com.example.d3nserver.quiz.dto.response;

import com.example.d3nserver.quiz.domain.SolvedQuiz;
import lombok.Data;

@Data
public class QuizSubmitResponseDto {
    private Long quizId;

    public QuizSubmitResponseDto(SolvedQuiz solvedQuiz){
        this.quizId = solvedQuiz.getQuiz().getId();
    }
}
