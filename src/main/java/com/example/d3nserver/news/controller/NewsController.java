package com.example.d3nserver.news.controller;

import com.example.d3nserver.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/news")
public class NewsController {
    private final NewsService newsService;
}
