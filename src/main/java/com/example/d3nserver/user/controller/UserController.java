package com.example.d3nserver.user.controller;

import com.example.d3nserver.auth.jwt.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.quiz.dto.SolvedQuizResponseDto;
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

    @PostMapping("/userForm/onboarding")
    public BaseResponse<User> saveUserForm(@ReqUser User user, @RequestBody UserDataFormDto inputForm){
        return BaseResponse.ofSuccess(userService.saveUserForm(user,inputForm));
    }

    @PostMapping("/solvedQuizList")
    public BaseResponse<List<SolvedQuizResponseDto>> getUserSolvedQuizList(@ReqUser User user){
        return BaseResponse.ofSuccess(solvedQuizService.getUserSolvedQuizList(user));
    }

    @PostMapping("/incorrectQuizList")
    public BaseResponse<List<SolvedQuizResponseDto>> getUserIncorrectQuizList(@ReqUser User user){
        return BaseResponse.ofSuccess(solvedQuizService.getUserIncorrectQuizList(user));
    }

}
