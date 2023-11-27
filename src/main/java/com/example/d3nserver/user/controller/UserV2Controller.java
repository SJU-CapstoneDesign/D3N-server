package com.example.d3nserver.user.controller;

import com.example.d3nserver.common.annotation.ApiDocumentResponse;
import com.example.d3nserver.common.annotation.ReqUser;
import com.example.d3nserver.common.dto.ResponseDto;
import com.example.d3nserver.quiz.service.SolvedQuizService;
import com.example.d3nserver.time.service.NewsReadingTimeService;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.dto.request.UserInfoUpdateRequestDto;
import com.example.d3nserver.user.dto.response.IsOnBoardingNeededResponseDto;
import com.example.d3nserver.user.dto.request.UserOnBoardRequestDto;
import com.example.d3nserver.user.dto.response.UserInfoResponseDto;
import com.example.d3nserver.user.dto.response.UserOnBoardResponseDto;
import com.example.d3nserver.user.dto.response.UserReferencedFieldResponseDto;
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
    private final NewsReadingTimeService newsReadingTimeService;

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

    @ApiDocumentResponse
    @Operation(summary = "User 정보 반환", description = "유저의 온보딩 정보를 반환한다.")
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@ReqUser User user){
        return ResponseDto.ok(userService.getUserInfo(user));
    }

    @ApiDocumentResponse
    @Operation(summary = "User 온보딩 정보 업데이트", description = "유저의 온보딩 정보를 업데이트한다.")
    @PatchMapping()
    public ResponseEntity<UserInfoResponseDto> editUserInfo(@ReqUser User user, @RequestBody UserInfoUpdateRequestDto requestDto){
        return ResponseDto.ok(userService.updateUserInfo(user, requestDto));
    }

    @ApiDocumentResponse
    @Operation(summary =  "User 추천 카테고리 반환", description = "유저가 읽은 최근 뉴스 10개를 바탕으로, 가장 많이 읽은 뉴스 카테고리를 반환")
    @GetMapping("/reference/field")
    public ResponseEntity<UserReferencedFieldResponseDto> getUserReferencedField(@ReqUser User user){
        return ResponseDto.ok(new UserReferencedFieldResponseDto(newsReadingTimeService.getCategoryOfMostReadingTime(user)));
    }

}
