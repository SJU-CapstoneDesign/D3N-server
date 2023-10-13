package com.example.d3nserver.news.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.domain.NewsType;
import lombok.Data;

@Data
public class NewsResponseDto {
    private Long id;
    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String url;

    public NewsResponseDto(News news) {
        this.id = news.getId();
        this.field = news.getField();
        this.newsType = news.getNewsType();
        this.title = news.getTitle();
        this.summary = news.getSummary();
        this.url = news.getUrl();
    }
}
