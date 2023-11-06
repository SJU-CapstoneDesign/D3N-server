package com.example.d3nserver.quiz.dto.request;

import lombok.Data;

@Data
public class SolvedQuizRequestDto {
    private Long quizId;
    private Integer selectedAnswer;
}
