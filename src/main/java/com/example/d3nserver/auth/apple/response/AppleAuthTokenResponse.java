package com.example.d3nserver.auth.apple.response;

import lombok.Getter;

@Getter
public class AppleAuthTokenResponse {
    private String access_token;
    private String refresh_token;
    private String id_token;
}
