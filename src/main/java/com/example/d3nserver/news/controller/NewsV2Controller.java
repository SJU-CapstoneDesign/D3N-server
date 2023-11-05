package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "News v1.1 API", description = "News 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/news")
public class NewsV2Controller {
    private final NewsService newsService;

    @ApiDocumentResponse
    @Operation(summary = "News list", description = "뉴스 id를 입력받아 해당하는 뉴스의 퀴즈 리스트를 반환한다.")
    @GetMapping("/list")
    public ResponseEntity<Page<NewsResponseDto>> getAllNews(@RequestParam @Parameter(description="페이지 인덱스, 0부터 시작")int pageIndex, @RequestParam @Parameter(description="페이지 크기")int pageSize) {
        return ResponseDto.ok(newsService.getAllNewsDtoPageList(pageIndex, pageSize));
    }

}
