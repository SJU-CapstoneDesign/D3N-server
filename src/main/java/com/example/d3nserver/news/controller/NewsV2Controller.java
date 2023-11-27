package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.news.dto.TodayHomeResponseDto;
import com.example.d3nserver.news.service.NewsService;
import com.example.d3nserver.user.domain.User;
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

import java.util.ArrayList;
import java.util.List;

@Tag(name = "News v1.1 API", description = "News 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/news")
public class NewsV2Controller {
    private final NewsService newsService;

    @ApiDocumentResponse
    @Operation(summary = "All News list", description = "Page index와 Page 크기를 받아 요청한 카테고리의 뉴스 페이지를 반환한다. (secondTime은 데이터가 없으면 0, selectedAnswer는 null을 반환)")
    @GetMapping("/list")
    public ResponseEntity<Page<NewsResponseDto>> getAllNews(@ReqUser User user, @RequestParam @Parameter(description="페이지 인덱스, 0부터 시작")int pageIndex, @RequestParam @Parameter(description="페이지 크기")int pageSize, @RequestParam(value = "field", defaultValue = "DEFAULT") @Parameter(description = "뉴스 필드(News의 Enum 타입 Field 기입)(미기입 시 DEFAULT)")Field field) {
        return ResponseDto.ok(newsService.getAllNewsPageList(user, pageIndex, pageSize, field));
    }

    @ApiDocumentResponse
    @Operation(summary = "Today News List", description = "type이 Recent일 경우 최신 뉴스만 반환, type이 My일 경우 맞춤형 뉴스만 반환, 지정 하지 않을 시 모두 반환")
    @GetMapping("/today/list")
    public ResponseEntity<List<TodayHomeResponseDto>> getTodayNews(@ReqUser User user, @RequestParam(value = "type", defaultValue = "All") String type) {
        return ResponseDto.ok(newsService.getTodayNewsList(user,type));
    }

}
