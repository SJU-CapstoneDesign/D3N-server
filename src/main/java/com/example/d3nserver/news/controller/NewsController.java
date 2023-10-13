package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.BaseResponse;
import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/list/today")
    public BaseResponse<List<NewsDTO>> getTodayNews() {
        return BaseResponse.ofSuccess(newsService.getTodayNewsDtoList());
    }

    @GetMapping("/list")
    public BaseResponse<Page<NewsDTO>> getAllNews(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return BaseResponse.ofSuccess(newsService.getAllNewsDtoPageList(pageIndex, pageSize));
    }

}
