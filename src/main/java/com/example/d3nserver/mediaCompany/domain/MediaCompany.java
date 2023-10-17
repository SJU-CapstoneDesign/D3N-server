package com.example.d3nserver.mediaCompany.domain;

import com.example.d3nserver.news.domain.News;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class MediaCompany {
    @Id
    private String id;
    private String name;
    private String logo;
    @OneToMany(mappedBy = "mediaCompany", fetch = FetchType.LAZY)
    private List<News> news;
}
