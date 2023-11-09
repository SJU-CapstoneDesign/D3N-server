package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.news.dto.NewsDTO;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.service.NewsService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "News API", description = "News 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    @Operation(summary = "News list", description = "Page index와 Page 크기를 받아 뉴스 페이지를 반환한다.")
    @GetMapping("/list")
    public BaseResponse<Page<NewsResponseDto>> getAllNews(@ReqUser User user, @RequestParam@Parameter(description="페이지 인덱스, 0부터 시작")int pageIndex, @RequestParam @Parameter(description="페이지 크기")int pageSize) {
        return BaseResponse.ofSuccess(newsService.getAllNewsDtoPageList(user, pageIndex, pageSize));
    }

}
