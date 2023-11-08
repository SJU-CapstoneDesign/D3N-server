package com.example.d3nserver.time.service;

import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.domain.QuizSolvingTime;
import com.example.d3nserver.time.repository.QuizSolvingTimeRepository;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizSolvingTimeService {
    private final QuizSolvingTimeRepository quizSolvingTimeRepository;

    public void updateQuizSolvingTime(User user, Long quizId, int secondTime){
        QuizSolvingTime solvingTime = quizSolvingTimeRepository.findByUserIdAndQuizId(user.getId(), quizId).orElseGet(
                ()-> new QuizSolvingTime(user.getId(), quizId)
        );
        solvingTime.updateSolvingTime(secondTime);
        quizSolvingTimeRepository.save(solvingTime);
    }
}
