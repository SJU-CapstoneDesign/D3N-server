package com.example.d3nserver.auth.jwt.provider;

import com.example.d3nserver.auth.jwt.authToken.AuthToken;
import com.example.d3nserver.user.domain.RoleType;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class AuthTokenProvider {
    @Value("${app.auth.tokenExpiry}")
    private String expiry;
    private final Key key;

    @Autowired
    public AuthTokenProvider(@Value("${app.auth.tokenSecret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public AuthToken createToken(String id, RoleType roleType, String expiry) {
        Date expiryDate = getExpiryDate(Long.parseLong(expiry));
        return new AuthToken(id, roleType, expiryDate, key);
    }
    public AuthToken createUserAppToken(String id, RoleType roleType) {
        return createToken(id, roleType, expiry);
    }

    public AuthToken createRefreshToken(String socialId){
        Date expiryDate = getExpiryDate(Long.parseLong(expiry) * 10);
        return new AuthToken(socialId, expiryDate, key);
    }
    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }
    public static Date getExpiryDate(Long expiry) {
        return new Date(System.currentTimeMillis() + expiry);
    }
}

