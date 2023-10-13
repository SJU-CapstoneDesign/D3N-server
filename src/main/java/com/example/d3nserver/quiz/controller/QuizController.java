package com.example.d3nserver.quiz.controller;

import com.example.d3nserver.quiz.dto.QuizResponseDto;
import com.example.d3nserver.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;
    @GetMapping(value = "/list")
    public List<QuizResponseDto> getQuizList(@RequestParam Long newsId){
        return quizService.getQuizList(newsId);
    }
}
