package com.example.d3nserver.time.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizSolvingTimeResponseDto {
    private String userId;
    private Long quizId;
    private Integer secondTime;
}
