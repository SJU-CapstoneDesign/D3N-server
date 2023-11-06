package com.example.d3nserver.time.service;

import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.repository.NewsReadingTimeRepository;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsReadingTimeService {
    private final NewsReadingTimeRepository newsReadingTimeRepository;

    public void updateNewsReadingTime(User user, Long newsId, int secondTime){
        NewsReadingTime readingTime = newsReadingTimeRepository.findByUserIdAndNewsId(user.getId(), newsId).orElseGet(
                ()-> new NewsReadingTime(newsId, user.getId())
        );
        readingTime.updateReadingTime(secondTime);
        newsReadingTimeRepository.save(readingTime);
    }
}
