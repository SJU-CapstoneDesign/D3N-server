package com.example.d3nserver.time.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class NewsReadingTime {
    @Id
    @GeneratedValue
    private Long id;
    private final Long newsId;
    private final String userId;
    private Integer secondTime;

    public NewsReadingTime(Long newsId, String userId){
        this.newsId = newsId;
        this.userId = userId;
        this.secondTime = 0;
    };

    public void updateReadingTime(int secondTime){
        this.secondTime = secondTime;
    }
}
