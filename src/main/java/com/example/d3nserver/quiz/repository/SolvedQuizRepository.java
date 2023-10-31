package com.example.d3nserver.quiz.repository;

import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Integer> {
    public List<SolvedQuiz> findAllByUser(User user);
}
