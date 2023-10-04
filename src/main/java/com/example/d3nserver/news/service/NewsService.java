package com.example.d3nserver.news.service;

import com.example.d3nserver.dto.NewsDTO;
import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.quiz.repository.QuizRepository;
import com.example.d3nserver.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final QuizService quizService;
    private final NewsRepository newsRepository;
    private final QuizRepository quizRepository;

    public List<News> getNewsList(){
        return newsRepository.findAll();
    }


    //나중에 유저의 선호 카테고리 정보를 매개변수로 받아, 맞춤형으로 제공할 예정
    public List<NewsDTO> getTodayNewsDtoList(){

        List<NewsDTO> todayNewsDtoList = new ArrayList<>();

        List<News> recentTenNews = newsRepository.findTop10ByOrderByCreatedAtDesc();


        //뉴스 DTO를 생성
        for(News news : recentTenNews){
            NewsDTO newsDTO = new NewsDTO(news);

            todayNewsDtoList.add(newsDTO);
        }


        return todayNewsDtoList;
    }

}
