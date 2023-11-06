package com.example.d3nserver.user.controller;

import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.quiz.dto.QuizSubmitRequestDto;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.dto.UserDataFormDto;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final SolvedQuizService solvedQuizService;

    @PostMapping("/onboard")
    public BaseResponse<User> saveUserForm(@ReqUser User user, @RequestBody UserDataFormDto inputForm){
        return BaseResponse.ofSuccess(userService.saveUserForm(user,inputForm));
    }

    @PostMapping("/list/solved")
    public BaseResponse<List<QuizSubmitRequestDto>> getUserSolvedQuizList(@ReqUser User user){
        return BaseResponse.ofSuccess(solvedQuizService.getUserSolvedQuizList(user));
    }

    @PostMapping("/list/incorrect")
    public BaseResponse<List<QuizSubmitRequestDto>> getUserIncorrectQuizList(@ReqUser User user){
        return BaseResponse.ofSuccess(solvedQuizService.getUserIncorrectQuizList(user));
    }

}
