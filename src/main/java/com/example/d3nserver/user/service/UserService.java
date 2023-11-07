package com.example.d3nserver.user.service;

import com.example.d3nserver.user.domain.RoleType;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.dto.response.IsOnBoardingNeededResponseDto;
import com.example.d3nserver.user.dto.request.UserOnBoardRequestDto;
import com.example.d3nserver.user.dto.response.UserOnBoardResponseDto;
import com.example.d3nserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findById(String socialId) {
        return userRepository.findById(socialId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getOrCreateUserBySocialId(String socialId) {
        Optional<User> user = userRepository.findById(socialId);
        return user.orElseGet(() -> userRepository.save(User.builder()
                .id(socialId)
                .roleType(RoleType.USER)
                .build()));
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public UserOnBoardResponseDto saveUserForm(User user, UserOnBoardRequestDto inputForm){
        user.setNickname(inputForm.getNickname());
        user.setGender(inputForm.getGender());
        user.setBirthDay(inputForm.getBirthDay());
        user.setNewsFields(inputForm.getNewsFields());
        return new UserOnBoardResponseDto(userRepository.save(user));
    }

    public IsOnBoardingNeededResponseDto getIsOnBoardingNeeded(User user){
        return new IsOnBoardingNeededResponseDto(user);
    }
}
