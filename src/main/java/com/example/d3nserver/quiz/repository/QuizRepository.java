package com.example.d3nserver.quiz.repository;

import com.example.d3nserver.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    public List<Quiz> findAllByNewsId(Long newsId);
    Optional<Quiz> findById(Long id);
}
