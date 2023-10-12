package com.example.d3nserver.news.service;

import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.quiz.repository.QuizRepository;
import com.example.d3nserver.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsDTO> getTodayNewsDtoList(){
        List<NewsDTO> todayNewsDtoList = new ArrayList<>();
        List<News> recentTenNews = newsRepository.findTop10ByOrderByCreatedAtDesc();
        for(News news : recentTenNews){
            NewsDTO newsDTO = new NewsDTO(news);
            todayNewsDtoList.add(newsDTO);
        }
        return todayNewsDtoList;
    }

    public Page<NewsDTO> getAllNewsDtoPageList(int pageIndex, int pageSize){
        List<NewsDTO> allNewsDtoList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<News> allNewsPage = newsRepository.findAllNewsByOrderByCreatedAtDesc(pageRequest);

        return allNewsPage.map(NewsDTO::new);
    }

}
