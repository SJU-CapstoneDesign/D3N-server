package com.example.d3nserver.auth.jwt.securityUserDetails;

import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService {
    private UserService userService;

    public UserDetails loadUserBySocialId(String socialId) {
        User user = userService.getOrCreateUserBySocialId(socialId);
        return new SecurityUserDetails(user);
    }
}
