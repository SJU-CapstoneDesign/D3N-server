package com.example.d3nserver.time.service;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import com.example.d3nserver.news.repository.NewsRepository;
import com.example.d3nserver.time.domain.NewsReadingTime;
import com.example.d3nserver.time.repository.NewsReadingTimeRepository;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NewsReadingTimeService {
    private final NewsReadingTimeRepository newsReadingTimeRepository;
    private final NewsRepository newsRepository;

    public void updateNewsReadingTime(User user, Long newsId, int secondTime){
        NewsReadingTime readingTime = newsReadingTimeRepository.findByUserIdAndNewsId(user.getId(), newsId).orElseGet(
                ()-> {
                    Optional<News> news = newsRepository.findById(newsId);
                    return news.map(value -> new NewsReadingTime(user.getId(), value)).orElse(null);
                }
        );
        if(readingTime == null)
            return;
        readingTime.updateReadingTime(secondTime);
        newsReadingTimeRepository.save(readingTime);
    }

    public Field getCategoryOfMostReadingTime(User user){
        Map<Field, Integer> categoryTimeMap = new HashMap<Field, Integer>();
        List<NewsReadingTime> newsReadingTimeList = newsReadingTimeRepository.findTop10ByUserIdOrderByCreatedAtDesc(user.getId());
        if(newsReadingTimeList.isEmpty())
            return Field.DEFAULT;
        int mostReadingTime = 0;
        Field mostReadingTimeField = Field.DEFAULT;
        for(NewsReadingTime readingTime : newsReadingTimeList) {
            Field field = readingTime.getNews().getField();
            if(categoryTimeMap.containsKey(field))
                categoryTimeMap.put(field, categoryTimeMap.get(field) + readingTime.getSecondTime());
            else
                categoryTimeMap.put(field, readingTime.getSecondTime());
            if (categoryTimeMap.get(field) > mostReadingTime) {
                mostReadingTime = categoryTimeMap.get(field);
                mostReadingTimeField = field;
            }
        }
        return mostReadingTimeField;
    }
}
