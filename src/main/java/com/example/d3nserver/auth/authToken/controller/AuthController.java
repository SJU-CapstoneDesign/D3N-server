package com.example.d3nserver.auth.authToken.controller;

import com.example.d3nserver.auth.authToken.response.AuthResponse;
import com.example.d3nserver.auth.authToken.service.AuthService;
import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API", description = "Auth 관련 api")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "토큰 refresh", description = "리프레쉬 토큰을 받아 해당 유저의 db에 존재하는 리프레쉬 토큰과 일치하는지 확인 후, 새로운 accessToken과 refreshToken을 발급한다.")
    @GetMapping("/refresh")
    public BaseResponse<AuthResponse> refreshToken (@RequestParam String refreshToken) throws CustomException {
        return BaseResponse.ofSuccess(authService.refreshToken(refreshToken));
    }
}