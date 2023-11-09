package com.example.d3nserver.time.domain;

import com.example.d3nserver.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class NewsReadingTime extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long newsId;
    private String userId;
    private Integer secondTime;

    public NewsReadingTime(String userId, Long newsId){
        this.newsId = newsId;
        this.userId = userId;
        this.secondTime = 0;
    };

    public void updateReadingTime(int secondTime){
        this.secondTime = secondTime;
    }
}
