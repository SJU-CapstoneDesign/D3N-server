package com.example.d3nserver.auth.apple.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppleLoginRequest {
    private String code;
    private String id_token;
}
