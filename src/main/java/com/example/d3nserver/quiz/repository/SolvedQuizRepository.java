package com.example.d3nserver.quiz.repository;

import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Integer> {
    List<SolvedQuiz> findAllByUser(User user);
    Optional<SolvedQuiz> findByUserAndQuiz(User user, Quiz quiz);
    Optional<SolvedQuiz> findByUserAndQuizId(User user, Long quizId);
}
