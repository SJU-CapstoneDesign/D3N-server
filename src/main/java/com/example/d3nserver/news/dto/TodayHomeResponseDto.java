package com.example.d3nserver.news.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodayHomeResponseDto {
    private String type;
    private String title;
    private String subtitle;
    private List<NewsResponseDto> newsList;

    public TodayHomeResponseDto(String type, List<NewsResponseDto> newsList){

        if(type.equals("Recent")){
            this.type = type;
            this.title = "최신 뉴스";
            this.subtitle = "최신 뉴스를 가져왔어요";
        }
        else if(type.equals("My")){
            this.type = type;
            this.title = "맞춤 뉴스";
            this.subtitle = "맞춤형 뉴스를 가져왔어요";
        }
        this.newsList = newsList;
    }
}
