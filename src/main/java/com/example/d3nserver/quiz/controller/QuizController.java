package com.example.d3nserver.quiz.controller;

import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.news.service.NewsService;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.quiz.dto.QuizResponseDto;
import com.example.d3nserver.quiz.dto.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.service.QuizService;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    @GetMapping(value = "/list")
    public BaseResponse<List<QuizResponseDto>> getQuizList(@RequestParam Long newsId){
        return BaseResponse.ofSuccess(quizService.getQuizList(newsId));
    }

    @PostMapping(value = "/list/submit")
    public BaseResponse<List<SolvedQuiz>> saveSolvedQuiz(@RequestParam("userId") String userId, @RequestBody List<SolvedQuizRequestDto> solvedQuizRequestDtoList){
        return BaseResponse.ofSuccess(quizService.saveSolvedQuizList(userId, solvedQuizRequestDtoList));
    }
}
