package com.example.d3nserver.time.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.dto.NewsReadingTimeRequestDto;
import com.example.d3nserver.time.service.NewsReadingTimeService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "NewsReadingTime v1.1 API", description = "뉴스 읽는 시간 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/news")
public class NewsReadingTimeController {
    private final NewsReadingTimeService newsReadingTimeService;

    @ApiDocumentResponse
    @Operation(summary = "NewsReadingTime Update", description = "뉴스 읽는 시간을 갱신합니다.")
    @PostMapping("/updatingTime")
    public ResponseEntity<Void> updateNewsReadingTime(@ReqUser User user, @RequestBody @Parameter(description = "newsReadingTime Dto의 리스트",
            content = @Content(schema = @Schema(implementation = NewsReadingTimeRequestDto.class))) NewsReadingTimeRequestDto requestDto){
        newsReadingTimeService.updateNewsReadingTime(user,requestDto.getNewsId(),requestDto.getSecondTime());
        return ResponseDto.ok(null);
    }
}
