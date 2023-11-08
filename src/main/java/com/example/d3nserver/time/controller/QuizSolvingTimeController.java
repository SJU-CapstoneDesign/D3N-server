package com.example.d3nserver.time.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.time.dto.request.NewsReadingTimeRequestDto;
import com.example.d3nserver.time.dto.request.QuizSolvingTimeRequestDto;
import com.example.d3nserver.time.dto.response.NewsReadingTimeResponseDto;
import com.example.d3nserver.time.dto.response.QuizSolvingTimeResponseDto;
import com.example.d3nserver.time.service.QuizSolvingTimeService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "QuizSolvingTime v1.1 API", description = "퀴즈 푸는 시간 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/quiz")
public class QuizSolvingTimeController {

    private final QuizSolvingTimeService quizSolvingTimeService;

    @ApiDocumentResponse
    @Operation(summary = "Quiz Solving Update", description = "퀴즈 푸는 시간을 갱신합니다.")
    @PostMapping("/updatingTime")
    public ResponseEntity<QuizSolvingTimeResponseDto> updateQuizSolvingTime(@ReqUser User user, @RequestBody @Parameter(description = "quizSolvingTime request Dto",
            content = @Content(schema = @Schema(implementation = QuizSolvingTimeRequestDto.class))) QuizSolvingTimeRequestDto requestDto){
        quizSolvingTimeService.updateQuizSolvingTime(user,requestDto.getQuizId(),requestDto.getSecondTime());
        return ResponseDto.ok(new QuizSolvingTimeResponseDto(user.getId(), requestDto.getQuizId(), requestDto.getSecondTime()));
    }
}
