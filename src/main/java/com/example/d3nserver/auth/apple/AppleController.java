package com.example.d3nserver.auth.apple;

import com.example.d3nserver.auth.apple.request.AppleLoginRequest;
import com.example.d3nserver.auth.common.response.AuthResponse;
import com.example.d3nserver.auth.jwt.ReqUser;
import com.example.d3nserver.common.base.BaseResponse;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/apple")
@RequiredArgsConstructor
public class AppleController {
    private final AppleAuthService appleAuthService;
    @PostMapping(value="/login")
    @ResponseBody
    public BaseResponse<AuthResponse> appleLogin(@RequestBody AppleLoginRequest appleLoginRequest) throws org.json.simple.parser.ParseException, IOException {
        AuthResponse authResponse = appleAuthService.login(appleLoginRequest);
        return BaseResponse.ofSuccess(authResponse);
    }

    @DeleteMapping(value="/unlink")
    public void appleUnlinkUser(@ReqUser User user) throws IOException{
        appleAuthService.unlinkUser(user);
    }
}
