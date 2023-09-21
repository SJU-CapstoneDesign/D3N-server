package com.example.d3nserver.quiz.domain;

import com.example.d3nserver.common.BaseEntity;
import com.example.d3nserver.news.domain.News;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Quiz extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    @ElementCollection
    private List<String> choiceList;
    private Integer answer;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="news_id")
    private News news;
    private String reason;
}