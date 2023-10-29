package com.example.d3nserver.user.dto;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.user.domain.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserDataFormDto {
    private String nickname;
    private Gender gender;
    private Integer birthYear;
    private List<Field> categoryList;
}