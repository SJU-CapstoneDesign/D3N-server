package com.example.d3nserver.news.service;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.quiz.dto.response.QuizResponseDto;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.repository.NewsReadingTimeRepository;
import com.example.d3nserver.time.service.NewsReadingTimeService;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsReadingTimeRepository newsReadingTimeRepository;
    private final NewsReadingTimeService newsReadingTimeService;
    private final QuizService quizService;

    public Page<NewsResponseDto> getAllNewsPageList(User user, int pageIndex, int pageSize, Field field){
        Page<News> newsPage;
        if(field == Field.DEFAULT)
            newsPage = newsRepository.findAllNewsByOrderByCreatedAtDesc(PageRequest.of(pageIndex, pageSize));
        else
            newsPage = newsRepository.findAllByFieldOrderByCreatedAtDesc(field, PageRequest.of(pageIndex, pageSize));

        return newsPage.map(news -> getResponseDto(user, news));
    }


    public List<NewsResponseDto> getRecentNewsList(User user){
        List<News> todayNewsList = newsRepository.findTopNByOrderByCreatedAtDesc(3);
        return todayNewsList.stream().map((news -> getResponseDto(user, news))).collect(Collectors.toList());
    }

    public List<NewsResponseDto> getUserReferencedNewsList(User user){
        List<NewsResponseDto> userReferencedNewsDtoList = new ArrayList<>();
        Field mostReadingTimeField = newsReadingTimeService.mostOfCategoryReadingTime(user);
        List<Field> userFieldList = user.getNewsFields();
        Collections.shuffle(userFieldList);
        List<Field> preferencedFiledList = userFieldList.subList(0, Math.min(3, userFieldList.size()));
        if(preferencedFiledList.size() == 1){
            if(mostReadingTimeField == Field.DEFAULT)
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),10).stream().map(news -> getResponseDto(user,news)).toList());
            else{
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(mostReadingTimeField,5).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),5).stream().map(news -> getResponseDto(user,news)).toList());
            }
        }
        else if(preferencedFiledList.size() == 2){
            if(mostReadingTimeField == Field.DEFAULT){
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),5).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(1),5).stream().map(news -> getResponseDto(user,news)).toList());
            }
            else{
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(mostReadingTimeField,4).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),3).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(1),3).stream().map(news -> getResponseDto(user,news)).toList());
            }
        }
        else{
            if(mostReadingTimeField == Field.DEFAULT){
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),3).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(1),3).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(2),4).stream().map(news -> getResponseDto(user,news)).toList());
            }
            else{
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(mostReadingTimeField,4).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(0),2).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(1),2).stream().map(news -> getResponseDto(user,news)).toList());
                userReferencedNewsDtoList.addAll(newsRepository.findTopNByField(preferencedFiledList.get(2),2).stream().map(news -> getResponseDto(user,news)).toList());
            }
        }
        Collections.shuffle(userReferencedNewsDtoList);
        return userReferencedNewsDtoList;
    }


    private NewsResponseDto getResponseDto(User user, News news){
        NewsResponseDto newsResponseDto = new NewsResponseDto(news);
        Optional<NewsReadingTime> newsReadingTime = newsReadingTimeRepository.findByUserIdAndNewsId(user.getId(), news.getId());
        newsReadingTime.ifPresent(value -> newsResponseDto.setSecondTime(value.getSecondTime()));
        List<QuizResponseDto> quizResponseDtoList = quizService.getQuizListByUser(user, news.getId());
        newsResponseDto.setQuizAnswerList(quizResponseDtoList.stream().map(QuizResponseDto::getAnswer).collect(Collectors.toList()));
        newsResponseDto.setSelectedAnswerList(quizResponseDtoList.stream().map(QuizResponseDto::getSelectedAnswer).collect(Collectors.toList()));
        return newsResponseDto;
    }
}
