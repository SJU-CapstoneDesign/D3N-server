package com.example.d3nserver.quiz.service;

import com.example.d3nserver.quiz.dto.QuizDTO;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    public List<QuizDTO> getQuizDtoList(News news){
        List<QuizDTO> quizDtoList = new ArrayList<>();
        List<Quiz> quizList = quizRepository.findAllByNews(news);
        for(Quiz quiz : quizList){
            QuizDTO quizDTO = new QuizDTO(quiz);
            quizDtoList.add(quizDTO);
        }
        return quizDtoList;
    }
}
