package com.example.d3nserver.user.dto.request;

import com.example.d3nserver.common.annotation.ValidEnum;
import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.user.domain.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserOnBoardRequestDto {
    private String nickname;
    @ValidEnum(enumClass = Gender.class)
    private Gender gender;
    private Long birthDay;
    @ValidEnum(enumClass = Field.class)
    private List<Field> newsFields;
}