package com.example.d3nserver.user.dto.response;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.user.domain.Gender;
import com.example.d3nserver.user.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseDto {
    private String nickname;
    private Gender gender;
    private Long birthDay;
    private List<Field> newsFields;

    public UserInfoResponseDto(User user) {
        nickname = user.getNickname();
        gender = user.getGender();
        birthDay = user.getBirthDay();
        newsFields = user.getNewsFields();
    }
}
