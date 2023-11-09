package com.example.d3nserver.news.service;

import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.repository.NewsReadingTimeRepository;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsReadingTimeRepository newsReadingTimeRepository;
    private final QuizService quizService;

    public List<NewsDTO> getTodayNewsDtoList(){
        List<NewsDTO> todayNewsDtoList = new ArrayList<>();
        List<News> recentTenNews = newsRepository.findTop10ByOrderByCreatedAtDesc();
        for(News news : recentTenNews){
            NewsDTO newsDTO = new NewsDTO(news);
            todayNewsDtoList.add(newsDTO);
        }
        return todayNewsDtoList;
    }

    public Page<NewsResponseDto> getAllNewsDtoPageList(@ReqUser User user, int pageIndex, int pageSize){
        Page<News> newsPage = newsRepository.findAllNewsByOrderByCreatedAtDesc(PageRequest.of(pageIndex, pageSize));
        return newsPage.map(news -> getResponseDto(user, news));
    }

    private NewsResponseDto getResponseDto(User user, News news){
        NewsResponseDto responseDto = new NewsResponseDto(news);
        Optional<NewsReadingTime> newsReadingTime = newsReadingTimeRepository.findByUserIdAndNewsId(user.getId(),news.getId());
        newsReadingTime.ifPresent(value -> responseDto.setSecondTime(value.getSecondTime()));
        
        return responseDto;
    }
}
