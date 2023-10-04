package com.example.d3nserver.news.controller;

import com.example.d3nserver.dto.NewsDTO;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    private final NewsRepository newsRepository;


    //최신 뉴스 3개 데이터를 오늘의 뉴스 화면에 진입하면 DTO로 변환 후 전달
    @GetMapping("/items/new/todayNews")
    public ResponseEntity<List<NewsDTO>> getTodayNews(){

        //최신 뉴스 10개를 무작위로 가져온다.
        List<NewsDTO> todayNewsList = newsService.getTodayNewsDtoList();

        //ok가 status 200 , 매개변수로 DTO
        return ResponseEntity.ok(todayNewsList);
    }
}


//위 url로 들어오면 NewsController로 들어온다.
//NewsController가 news url을 담당한다.
//default로는 다 담당할 수 있음

//성능 때문에 명시적으로 RequesetMapping 표기

