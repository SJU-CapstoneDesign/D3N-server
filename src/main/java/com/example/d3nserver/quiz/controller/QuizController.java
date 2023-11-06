package com.example.d3nserver.quiz.controller;

import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.quiz.dto.response.QuizResponseDto;
import com.example.d3nserver.quiz.dto.request.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.dto.request.QuizSubmitRequestDto;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quiz API", description = "Quiz 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;
    private final SolvedQuizService solvedQuizService;

    @Operation(summary = "Quiz list", description = "뉴스 id를 입력받아 해당하는 뉴스의 퀴즈 리스트를 반환한다.")
    @GetMapping(value = "/list")
    public BaseResponse<List<QuizResponseDto>> getQuizList(@RequestParam @Parameter(description="뉴스 id")Long newsId){
        return BaseResponse.ofSuccess(quizService.getQuizList(newsId));
    }

    @PostMapping(value = "/list/submit")
    public BaseResponse<List<QuizSubmitRequestDto>> submitQuiz(@ReqUser User user, @RequestBody List<SolvedQuizRequestDto> solvedQuizRequestDtoList) throws CustomException {
        return BaseResponse.ofSuccess(solvedQuizService.saveSolvedQuizList(user, solvedQuizRequestDtoList));
    }
}
