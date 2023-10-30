package com.example.d3nserver.auth.common;

import com.example.d3nserver.auth.common.response.AuthResponse;
import com.example.d3nserver.auth.jwt.authToken.AuthToken;
import com.example.d3nserver.auth.jwt.provider.AuthTokenProvider;
import com.example.d3nserver.common.base.BaseException;
import com.example.d3nserver.common.base.BaseResponseStatus;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthTokenProvider authTokenProvider;
    private final UserService userService;
    public AuthResponse refreshToken(String refreshToken) throws BaseException {
        AuthToken authToken = authTokenProvider.convertAuthToken(refreshToken);
        if (!authToken.validate())
            throw new BaseException(BaseResponseStatus.INVALID_JWT_TOKEN);
        String socialId = authToken.getTokenSubject();
        User user = userService.findById(socialId).orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_REFRESH_TOKEN));
        if (!user.getRefreshToken().equals(refreshToken))
            throw new BaseException(BaseResponseStatus.INCORRECT_REFRESH_TOKEN);
        AuthToken newAuthToken = authTokenProvider.createUserAppToken(socialId, user.getRoleType());
        return new AuthResponse(newAuthToken.getToken(), refreshToken);
    }
}
