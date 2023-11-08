package com.example.d3nserver.quiz.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.quiz.dto.response.QuizResponseDto;
import com.example.d3nserver.quiz.dto.request.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.dto.response.QuizSubmitResponseDto;
import com.example.d3nserver.quiz.dto.response.SolvedQuizResponseDto;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiDocumentResponse
    @Operation(summary = "Quiz list", description = "뉴스 id를 입력받아 해당하는 뉴스의 퀴즈 리스트를 반환한다. 이때 이미 푼 문제라면 유저가 고른 정답을 함께 반환한다")
    @GetMapping(value = "/list")
    public ResponseEntity<List<QuizResponseDto>> getQuizList(@ReqUser User user, @RequestParam @Parameter(description="뉴스 id")Long newsId){
        return ResponseDto.ok(quizService.getQuizListByUser(user, newsId));
    }

    @ApiDocumentResponse
    @Operation(summary = "Quiz 제출", description = "퀴즈와 유저가 고른 정답을 받아 저장한다.")
    @ApiResponse(responseCode = "404", description = "해당하는 퀴즈가 존재하지 않음.")
    @PostMapping(value = "/list/submit")
    public ResponseEntity<List<QuizSubmitResponseDto>> submitQuiz(@ReqUser User user, @RequestBody List<SolvedQuizRequestDto> solvedQuizRequestDtoList) throws CustomException {
        return ResponseDto.ok(solvedQuizService.saveSolvedQuizList(user, solvedQuizRequestDtoList));
    }

    @ApiDocumentResponse
    @Operation(summary = "User 푼 문제 리스트", description = "해당 유저가 푼 문제 리스트를 반환한다.")
    @GetMapping("/list/solved")
    public ResponseEntity<List<SolvedQuizResponseDto>> getUserSolvedQuizList(@ReqUser User user){
        return ResponseDto.ok(solvedQuizService.getUserSolvedQuizList(user));
    }

    @ApiDocumentResponse
    @Operation(summary = "User 틀린 문제 리스트", description = "해당 유저가 푼 문제 중 틀린 문제 리스트를 반환한다.")
    @GetMapping("/list/incorrect")
    public ResponseEntity<List<SolvedQuizResponseDto>> getUserIncorrectQuizList(@ReqUser User user){
        return ResponseDto.ok(solvedQuizService.getUserIncorrectQuizList(user));
    }
}
