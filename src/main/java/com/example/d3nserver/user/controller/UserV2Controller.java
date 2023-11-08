package com.example.d3nserver.user.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.dto.response.IsOnBoardingNeededResponseDto;
import com.example.d3nserver.user.dto.request.UserOnBoardRequestDto;
import com.example.d3nserver.user.dto.response.UserOnBoardResponseDto;
import com.example.d3nserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User v1.1 API", description = "User 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.1/user")
@Slf4j
public class UserV2Controller {
    private final UserService userService;
    private final SolvedQuizService solvedQuizService;

    @ApiDocumentResponse
    @Operation(summary = "User 온보딩", description = "온보딩 데이터를 받아 user에 저장한다.")
    @PostMapping("/onboard")
    public ResponseEntity<UserOnBoardResponseDto> saveUserForm(@ReqUser User user, @RequestBody UserOnBoardRequestDto inputForm){
        return ResponseDto.ok(userService.saveUserForm(user,inputForm));
    }

    @ApiDocumentResponse
    @Operation(summary = "User 온보딩 필요 여부", description = "온보딩 과정이 필요한 유저인지 반환한다.")
    @GetMapping("/onboard/needed")
    public ResponseEntity<IsOnBoardingNeededResponseDto> getIsOnBoardingNeeded(@ReqUser User user){
        return ResponseDto.ok(userService.getIsOnBoardingNeeded(user));
    }

}
