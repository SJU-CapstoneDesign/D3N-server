package com.example.d3nserver.time.repository;

import com.example.d3nserver.time.domain.NewsReadingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface NewsReadingTimeRepository extends JpaRepository<NewsReadingTime, Long> {
    public Optional<NewsReadingTime> findByUserIdAndNewsId(String userId, Long newsId);
}
