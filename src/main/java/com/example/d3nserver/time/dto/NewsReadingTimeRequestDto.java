package com.example.d3nserver.time.dto;

import lombok.Data;

@Data
public class NewsReadingTimeRequestDto {
    private Long newsId;
    private Integer secondTime;
}
