package com.example.d3nserver.quiz.dto.response;

import com.example.d3nserver.news.dto.NewsResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class SolvedQuizResponseDto {
    private Long id;
    private String question;
    private List<String> choiceList;
    private Integer answer;
    private String reason;
    private Integer selectedAnswer;
    private NewsResponseDto news;
}
