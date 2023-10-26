package com.example.d3nserver.auth.apple;

import com.example.d3nserver.auth.apple.request.AppleLoginRequest;
import com.example.d3nserver.auth.apple.response.AppleAuthTokenResponse;
import com.example.d3nserver.auth.common.response.AuthResponse;
import com.example.d3nserver.auth.jwt.authToken.AuthToken;
import com.example.d3nserver.auth.jwt.provider.AuthTokenProvider;
import com.example.d3nserver.common.base.BaseException;
import com.example.d3nserver.common.util.parser.JwtParser;
import com.example.d3nserver.user.domain.MemberProvider;
import com.example.d3nserver.user.domain.RoleType;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AppleAuthService {
    private final UserService userService;
    private final AuthTokenProvider authTokenProvider;
    @Value("${app.auth.keyId}")
    private String appleSignKeyId;
    @Value("${app.auth.teamId}")
    private String appleTeamId;
    @Value("${app.auth.appleKeyId}")
    private String keyId;
    @Value("${app.auth.appleClientId}")
    private String clientId;

    public AuthResponse login(AppleLoginRequest appleLoginRequest) throws ParseException, IOException {
        String clientSecret = this.createClientSecret();
        AppleAuthTokenResponse response = this.GenerateAuthToken(appleLoginRequest.getCode(), clientSecret);
        String appleRefreshToken = response.getRefresh_token();
        String socialId = JwtParser.getSocialIdFromJwt(appleLoginRequest.getId_token());
        AuthToken refreshToken = authTokenProvider.createRefreshToken(socialId);
        AuthToken appToken;
        Optional<User> user = userService.findById(socialId);
        if (user.isEmpty()){
            User newUser = User.builder()
                    .id(socialId)
                    .roleType(RoleType.USER)
                    .appleRefreshToken(appleRefreshToken)
                    .memberProvider(MemberProvider.APPLE)
                    .refreshToken(refreshToken.getToken())
                    .build();
            userService.save(newUser);
            appToken = authTokenProvider.createUserAppToken(socialId, RoleType.USER);
        }
        else {
            User member = user.get();
            member.setRefreshToken(refreshToken.getToken());
            member.setAppleRefreshToken(appleRefreshToken);
            userService.save(member);
            appToken = authTokenProvider.createUserAppToken(socialId, member.getRoleType());
        }
        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public void unlinkUser(User user) throws IOException {
        String appleRefreshToken = user.getAppleRefreshToken();
        userService.delete(user);
        String clientSecret = this.createClientSecret();

        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String authUrl = "https://appleid.apple.com/auth/revoke";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("token", appleRefreshToken);
        params.add("token_type_hint", "refresh_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        restTemplate.postForEntity(authUrl, httpEntity, AppleAuthTokenResponse.class);
    }

    private String createClientSecret() throws IOException {
        Date expirationDate = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("kid", appleSignKeyId);
        jwtHeader.put("alg", "ES256");

        return Jwts.builder()
                .setHeaderParams(jwtHeader)
                .setIssuer(appleTeamId)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발행 시간 - UNIX 시간
                .setExpiration(expirationDate) // 만료 시간
                .setAudience("https://appleid.apple.com")
                .setSubject(clientId)
                .signWith(SignatureAlgorithm.ES256, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/AuthKey_" + keyId + ".p8");
        System.out.println(resource.getURI());
        String privateKey = new String(resource.getInputStream().readAllBytes());
        Reader pemReader = new StringReader(privateKey);
        PEMParser pemParser = new PEMParser(pemReader);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
        return converter.getPrivateKey(object);
    }

    public AppleAuthTokenResponse GenerateAuthToken(String code, String client_secret) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String authUrl = "https://appleid.apple.com/auth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", client_secret);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<AppleAuthTokenResponse> response = restTemplate.postForEntity(authUrl, httpEntity, AppleAuthTokenResponse.class);
        return response.getBody();
    }

}
