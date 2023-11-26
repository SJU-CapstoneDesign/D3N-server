package com.example.d3nserver.news.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
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

@Tag(name = "News v1.2 API", description = "News 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.2/news")
public class NewsV3Controller {
    private final NewsService newsService;

    @ApiDocumentResponse
    @Operation(summary = "Today News List", description = "type이 Recent일 경우 최신 뉴스만 반환, type이 My일 경우 맞춤형 뉴스만 반환, 지정 하지 않을 시 모두 반환")
    @GetMapping("/today/list")
    public ResponseEntity<List<TodayHomeResponseDto>> getTodayNews(@ReqUser User user, @RequestParam(value = "type", defaultValue = "All") String type) {
        List<TodayHomeResponseDto> responseDtoList = new ArrayList<>();
        if(type.equals("Recent")){
            responseDtoList.add(new TodayHomeResponseDto("Recent",newsService.getTodayNewsListV3(user)));
        }
        else if(type.equals("My")){
            responseDtoList.add(new TodayHomeResponseDto("My",newsService.getUserReferencedNewsListV3(user)));
        }
        else{
            responseDtoList.add(new TodayHomeResponseDto("Recent",newsService.getTodayNewsListV3(user)));
            responseDtoList.add(new TodayHomeResponseDto("My",newsService.getUserReferencedNewsListV3(user)));
        }
        return ResponseDto.ok(responseDtoList);
    }

}
