package com.example.d3nserver.news.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.domain.NewsType;
import com.example.d3nserver.quiz.domain.Quiz;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class NewsResponseDto {
    private Long id;
    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String url;
    private String mediaCompanyId;
    private String mediaCompanyLogo;
    private String mediaCompanyName;
    private Integer secondTime;
    private List<Integer> quizAnswerList;
    private List<Integer> selectedAnswerList;
    private Integer level;

    public NewsResponseDto(News news) {
        this.id = news.getId();
        this.field = news.getField();
        this.newsType = news.getNewsType();
        this.title = excludingHtmlTag(news.getTitle());
        this.summary = excludingHtmlTag(news.getSummary());
        this.url = news.getUrl();
        this.mediaCompanyId = news.getMediaCompany().getId();
        this.mediaCompanyLogo = news.getMediaCompany().getLogo();
        this.mediaCompanyName = news.getMediaCompany().getName();
        this.setSecondTime(0);
        this.level = getAvg(news.getQuizList().stream().map(Quiz::getLevel).collect(Collectors.toList()));
    }

    private Integer getAvg(List<Integer> levelList){
        Integer sum = 0;
        for (Integer level: levelList)
            sum += level;
        return (int)(sum / 3 + 0.5);
    }

    public String excludingHtmlTag(String htmlContent){
        Document doc = Jsoup.parse(htmlContent);
        return doc.text();
    }
}
