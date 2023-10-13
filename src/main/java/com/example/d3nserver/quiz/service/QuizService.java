package com.example.d3nserver.quiz.service;

import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.dto.QuizResponseDto;
import com.example.d3nserver.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    public List<QuizResponseDto> getQuizList(Long newsId){
        List<Quiz> quizList = quizRepository.findAllByNewsId(newsId);
        return quizList.stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }
}
