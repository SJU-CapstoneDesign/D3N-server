package com.example.d3nserver.quiz.service;

import com.example.d3nserver.dto.NewsDTO;
import com.example.d3nserver.dto.QuizDTO;
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



    //매개변수로 들어온 News에 연관된 퀴즈들을 리스트 형식으로 불러옵니다.
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
