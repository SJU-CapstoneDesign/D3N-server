package com.example.d3nserver.auth.apple;

import com.example.d3nserver.auth.apple.request.AppleLoginRequest;
import com.example.d3nserver.auth.common.response.AuthResponse;
import com.example.d3nserver.auth.jwt.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Tag(name = "Apple API", description = "Apple Oauth 관련 api")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth/apple")
@RequiredArgsConstructor
public class AppleController {
    private final AppleAuthService appleAuthService;
    @Operation(summary = "애플 로그인", description = "애플 로그인을 통해 받은 정보를 이용해 유저를 생성 혹은 식별 후 access token 맟 refresh 토큰 발급")
    @PostMapping(value="/login")
    @ResponseBody
    public BaseResponse<AuthResponse> appleLogin(@RequestBody AppleLoginRequest appleLoginRequest) throws org.json.simple.parser.ParseException, IOException {
        AuthResponse authResponse = appleAuthService.login(appleLoginRequest);
        return BaseResponse.ofSuccess(authResponse);
    }

    @Operation(summary = "애플 회원탈퇴", description = "현재 로그인한 유저를 회원탈퇴 처리한다.")
    @DeleteMapping(value="/unlink")
    public void appleUnlinkUser(@ReqUser User user) throws IOException{
        appleAuthService.unlinkUser(user);
    }
}
