package com.example.d3nserver.quiz.repository;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    public List<Quiz> findAllByNews(News news);


}
