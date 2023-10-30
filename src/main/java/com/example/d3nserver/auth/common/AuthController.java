package com.example.d3nserver.auth.common;

import com.example.d3nserver.auth.common.response.AuthResponse;
import com.example.d3nserver.auth.jwt.authToken.AuthToken;
import com.example.d3nserver.auth.jwt.provider.AuthTokenProvider;
import com.example.d3nserver.common.base.BaseException;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.common.base.BaseResponseStatus;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthTokenProvider authTokenProvider;
    private final UserService userService;

    @GetMapping("/refresh")
    public BaseResponse<AuthResponse> refreshToken (@RequestParam String refreshToken) throws BaseException {
        AuthToken authToken = authTokenProvider.convertAuthToken(refreshToken);
        if (!authToken.validate())
            throw new BaseException(BaseResponseStatus.INVALID_JWT_TOKEN);
        String socialId = authToken.getTokenSubject();
        User user = userService.findById(socialId).orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_REFRESH_TOKEN));
        if (!user.getRefreshToken().equals(refreshToken))
            throw new BaseException(BaseResponseStatus.INCORRECT_REFRESH_TOKEN);
        AuthToken newAuthToken = authTokenProvider.createUserAppToken(socialId, user.getRoleType());
        AuthResponse authResponse = new AuthResponse(newAuthToken.getToken(), refreshToken);
        return BaseResponse.ofSuccess(authResponse);
    }
}