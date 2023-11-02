package com.example.d3nserver.quiz.controller;

import com.example.d3nserver.auth.jwt.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.quiz.dto.QuizResponseDto;
import com.example.d3nserver.quiz.dto.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.dto.SolvedQuizResponseDto;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quiz v1.1 API", description = "Quiz 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1.1/quiz")
public class QuizV2Controller {
    private final QuizService quizService;
    private final SolvedQuizService solvedQuizService;

    @Operation(summary = "Quiz list", description = "뉴스 id를 입력받아 해당하는 뉴스의 퀴즈 리스트를 반환한다.")
    @GetMapping(value = "/list")
    public ResponseEntity<List<QuizResponseDto>> getQuizList(@RequestParam @Parameter(description="뉴스 id")Long newsId){
        return ResponseDto.ok(quizService.getQuizList(newsId));
    }

    @PostMapping(value = "/list/submit")
    public ResponseEntity<List<SolvedQuizResponseDto>> submitQuiz(@ReqUser User user, @RequestBody List<SolvedQuizRequestDto> solvedQuizRequestDtoList){
        return ResponseDto.ok(solvedQuizService.saveSolvedQuizList(user, solvedQuizRequestDtoList));
    }
}
