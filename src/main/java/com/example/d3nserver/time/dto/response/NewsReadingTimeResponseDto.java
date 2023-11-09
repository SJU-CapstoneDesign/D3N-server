package com.example.d3nserver.time.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsReadingTimeResponseDto {
    private String userId;
    private Long newsId;
    private Integer secondTime;
}
