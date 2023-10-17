package com.example.d3nserver.mediaCompany.domain;

import com.example.d3nserver.news.domain.News;
import jakarta.persistence.*;

@Entity
public class MediaCompany {
    @Id
    private String id;
    private String name;
    private String logo;
    @OneToOne(mappedBy = "mediaCompany", fetch = FetchType.LAZY)
    private News news;
}
