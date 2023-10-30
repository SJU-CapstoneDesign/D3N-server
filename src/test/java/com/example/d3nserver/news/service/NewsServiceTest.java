package com.example.d3nserver.news.service;

import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.dto.NewsResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class NewsServiceTest {
    @Autowired
    NewsService newsService;

    @Test
    void getTodayNewsDtoList() {
        List<NewsDTO> todayNewsDtoList = newsService.getTodayNewsDtoList();
    }

    @Test
    void getAllNewsDtoList(){
        Page<NewsResponseDto> allNewsDtoPageList = newsService.getAllNewsDtoPageList(1, 3);
        List<NewsResponseDto> content = allNewsDtoPageList.getContent();

        System.out.println("title : " + content.get(1).getTitle());
        System.out.println("summary : " + content.get(1).getSummary());
    }
}