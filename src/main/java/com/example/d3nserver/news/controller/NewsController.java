package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    // TODO: iOS 측 마이그레이션 끝나면 삭제
    @GetMapping("/list/today")
    public BaseResponse<List<NewsDTO>> getTodayNews() {
        return BaseResponse.ofSuccess(newsService.getTodayNewsDtoList());
    }

    @GetMapping("/list")
    public BaseResponse<Page<NewsResponseDto>> getAllNews(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return BaseResponse.ofSuccess(newsService.getAllNewsDtoPageList(pageIndex, pageSize));
    }

}
