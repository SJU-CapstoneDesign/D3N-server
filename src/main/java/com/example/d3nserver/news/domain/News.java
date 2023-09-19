package com.example.d3nserver.news.domain;

import com.example.d3nserver.common.BaseEntity;
import com.example.d3nserver.quiz.domain.Quiz;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Field field;
    private NewsType newsType;
    private String title;
    private String summary;
    private String content;
    private String url;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizList;
}
