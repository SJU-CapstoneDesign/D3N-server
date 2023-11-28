package com.example.d3nserver.quiz.domain;

import com.example.d3nserver.common.base.BaseEntity;
import com.example.d3nserver.news.domain.News;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Quiz extends BaseEntity{
    @Id
    private Long id;
    private String question;
    @ElementCollection
    private List<String> choiceList;
    private Integer answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="news_id")
    private News news;
    private String reason;
    private Integer level;
}