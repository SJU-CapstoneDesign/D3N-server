package com.example.d3nserver.user.dto.response;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.user.domain.Gender;
import com.example.d3nserver.user.domain.User;
import lombok.Data;
import java.util.List;

@Data
public class UserOnBoardResponseDto {
    private String id;
    private String nickname;
    private Gender gender;
    private Integer birthYear;
    private List<Field> categoryList;

    public UserOnBoardResponseDto(User user) {
        id = user.getId();
        nickname = user.getNickname();
        gender = user.getGender();
        birthYear = user.getBirthYear();
        categoryList = user.getNewsFields();
    }
}
