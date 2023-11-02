package com.example.d3nserver.quiz.dto;

import com.example.d3nserver.quiz.domain.SolvedQuiz;
import lombok.Data;

import java.util.List;

@Data
public class SolvedQuizRequestDto {
    private Long quizId;
    private Integer selectedAnswer;
    private Integer quizAnswer;
}
