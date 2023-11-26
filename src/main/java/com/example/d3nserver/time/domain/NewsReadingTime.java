package com.example.d3nserver.time.domain;

import com.example.d3nserver.common.base.BaseEntity;
import com.example.d3nserver.news.domain.News;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class NewsReadingTime extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
    private String userId;
    private Integer secondTime;

    public NewsReadingTime(String userId, News news){
        this.news = news;
        this.userId = userId;
        this.secondTime = 0;
    };

    public void updateReadingTime(int secondTime){
        this.secondTime = secondTime;
    }
}
