package com.example.d3nserver.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.domain.NewsType;
import com.example.d3nserver.quiz.domain.Quiz;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NewsDTO {

    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String content;
    private String url;

    private List<QuizDTO> quizList;


    protected NewsDTO(){}

    public NewsDTO(News news, List<QuizDTO> quizDTOList){
        this.field = news.getField();
        this.newsType = news.getNewsType();
        this.title = news.getTitle();
        this.summary = news.getSummary();
        this.content = news.getContent();
        this.url = news.getUrl();

        this.quizList.addAll(quizDTOList);

    }
}
