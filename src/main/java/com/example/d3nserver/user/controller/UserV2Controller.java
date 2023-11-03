package com.example.d3nserver.user.controller;

import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.quiz.dto.SolvedQuizResponseDto;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.dto.IsOnBoardingNeededResponseDto;
import com.example.d3nserver.user.dto.UserDataFormDto;
import com.example.d3nserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User v1.1 API", description = "User 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/user")
@Slf4j
public class UserV2Controller {
    private final UserService userService;
    private final SolvedQuizService solvedQuizService;

    @Operation(summary = "User 온보딩", description = "온보딩 데이터를 받아 user에 저장한다.")
    @PostMapping("/onboard")
    public ResponseEntity<User> saveUserForm(@ReqUser User user, @RequestBody UserDataFormDto inputForm){
        return ResponseDto.ok(userService.saveUserForm(user,inputForm));
    }

    @Operation(summary = "User 푼 문제 리스트", description = "해당 유저가 푼 문제 리스트를 반환한다.")
    @PostMapping("/list/solved")
    public ResponseEntity<List<SolvedQuizResponseDto>> getUserSolvedQuizList(@ReqUser User user){
        return ResponseDto.ok(solvedQuizService.getUserSolvedQuizList(user));
    }

    @Operation(summary = "User 틀린 문제 리스트", description = "해당 유저가 푼 문제 중 틀린 문제 리스트를 반환한다.")
    @PostMapping("/list/incorrect")
    public ResponseEntity<List<SolvedQuizResponseDto>> getUserIncorrectQuizList(@ReqUser User user){
        return ResponseDto.ok(solvedQuizService.getUserIncorrectQuizList(user));
    }

    @Operation(summary = "User 온보딩 필요 여부", description = "온보딩 과정이 필요한 유저인지 반환한다.")
    @GetMapping("/onboard/needed")
    public ResponseEntity<IsOnBoardingNeededResponseDto> getIsOnBoardingNeeded(@ReqUser User user){
        return ResponseDto.ok(userService.getIsOnBoardingNeeded(user));
    }

}
