package com.example.d3nserver.news.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.domain.NewsType;
import com.example.d3nserver.quiz.dto.QuizResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class NewsDTO {

    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String content;
    private String url;
    private List<QuizResponseDto> quizList;

    public NewsDTO(News news){
        this.field = news.getField();
        this.newsType = news.getNewsType();
        this.title = news.getTitle();
        this.summary = news.getSummary();
        this.content = news.getContent();
        this.url = news.getUrl();
        this.quizList = news.getQuizList().stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }
}
