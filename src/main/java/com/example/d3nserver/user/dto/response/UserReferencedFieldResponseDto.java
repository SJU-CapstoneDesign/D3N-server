package com.example.d3nserver.user.dto.response;

import com.example.d3nserver.news.domain.Field;
import lombok.Data;

@Data
public class UserReferencedFieldResponseDto {
    private Field field;

    public UserReferencedFieldResponseDto(Field field){
        this.field = field;
    }
}
