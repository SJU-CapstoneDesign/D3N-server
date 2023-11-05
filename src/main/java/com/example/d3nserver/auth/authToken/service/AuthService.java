package com.example.d3nserver.auth.authToken.service;

import com.example.d3nserver.auth.authToken.response.AuthResponse;
import com.example.d3nserver.auth.jwt.authToken.AuthToken;
import com.example.d3nserver.auth.jwt.provider.AuthTokenProvider;
import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.common.exception.ErrorCode;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthTokenProvider authTokenProvider;
    private final UserService userService;
    public AuthResponse refreshToken(String refreshToken) throws CustomException {
        AuthToken authToken = authTokenProvider.convertAuthToken(refreshToken);
        if (!authToken.validate())
            throw new CustomException(ErrorCode.INVALID_JWT_TOKEN);
        String socialId = authToken.getTokenSubject();
        User user = userService.findById(socialId).orElseThrow(() -> new CustomException(ErrorCode.INVALID_REFRESH_TOKEN));
        if (!user.getRefreshToken().equals(refreshToken))
            throw new CustomException(ErrorCode.INCORRECT_REFRESH_TOKEN);
        AuthToken newAuthToken = authTokenProvider.createUserAppToken(socialId, user.getRoleType());
        return new AuthResponse(newAuthToken.getToken(), refreshToken);
    }
}
