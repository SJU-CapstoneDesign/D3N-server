package com.example.d3nserver.news.repository;

import com.example.d3nserver.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
