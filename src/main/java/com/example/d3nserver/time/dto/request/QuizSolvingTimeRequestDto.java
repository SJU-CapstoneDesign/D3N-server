package com.example.d3nserver.time.dto.request;

import lombok.Data;

@Data
public class QuizSolvingTimeRequestDto {
    private Long quizId;
    private Integer secondTime;
}
