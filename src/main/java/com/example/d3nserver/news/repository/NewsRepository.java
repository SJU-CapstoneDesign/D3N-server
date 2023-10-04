package com.example.d3nserver.news.repository;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    public List<News> findAllByField(Field field);

    public List<News> findTop10ByOrderByCreatedAtDesc();

}
