package com.example.d3nserver.time.repository;

import com.example.d3nserver.time.domain.QuizSolvingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizSolvingTimeRepository extends JpaRepository<QuizSolvingTime, Long> {
    public Optional<QuizSolvingTime> findByUserIdAndQuizId(String userId, Long quizId);
}
