package com.example.d3nserver.config;

import com.example.d3nserver.auth.jwt.filter.JwtAuthenticationFilter;
import com.example.d3nserver.auth.jwt.provider.AuthTokenProvider;
import com.example.d3nserver.auth.jwt.securityUserDetails.SecurityUserDetailsService;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final AuthTokenProvider authTokenProvider;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers(
                "/api/v1.1/auth/apple/login",
                "/api/v1.1/auth/refresh",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                /**
                 * Todo: 프론트에서 로그인 작업 완료하면 제거
                 */
                "/api/v1/**"
        );
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/v1/**").authenticated();
                    request.anyRequest().permitAll();
                })
                .anonymous(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .apply(new JwtFilter());
        return http.build();
    }
    public class JwtFilter extends AbstractHttpConfigurer<JwtFilter, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            SecurityUserDetailsService userDetailsService = new SecurityUserDetailsService(userService);
            JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authTokenProvider, userDetailsService);
            http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        }
    }
}
