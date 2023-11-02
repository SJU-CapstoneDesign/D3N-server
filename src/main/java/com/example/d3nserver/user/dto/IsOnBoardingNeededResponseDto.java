package com.example.d3nserver.user.dto;

import com.example.d3nserver.user.domain.User;
import lombok.Data;

@Data
public class IsOnBoardingNeededResponseDto {
    private Boolean isOnBoardingNeeded;

    public IsOnBoardingNeededResponseDto(User user) {
        this.isOnBoardingNeeded = user.getNickname().isEmpty();
    }
}
