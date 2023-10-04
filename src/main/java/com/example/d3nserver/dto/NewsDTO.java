package com.example.d3nserver.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.domain.NewsType;
import com.example.d3nserver.quiz.domain.Quiz;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class NewsDTO {

    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String content;
    private String url;

    private List<QuizDTO> quizList;



    public NewsDTO(News news){
        this.field = news.getField();
        this.newsType = news.getNewsType();
        this.title = news.getTitle();
        this.summary = news.getSummary();
        this.content = news.getContent();
        this.url = news.getUrl();
    
        //QuizDTO 리스트 생성
        for(Quiz quiz : news.getQuizList()){

            QuizDTO quizDTO = new QuizDTO(quiz);

            quizList.add(quizDTO);
        }
    }
}
