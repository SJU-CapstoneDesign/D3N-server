package com.example.d3nserver.news.domain;

import com.example.d3nserver.common.BaseEntity;
import com.example.d3nserver.mediaCompany.domain.MediaCompany;
import com.example.d3nserver.quiz.domain.Quiz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class News extends BaseEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Field field;
    @Enumerated(EnumType.STRING)
    private NewsType newsType;
    private String title;
    private String summary;
    private String content;
    private String url;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz> quizList;
    @OneToOne
    @JoinColumn(name = "media_company_id")
    private MediaCompany mediaCompany;
}
