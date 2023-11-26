package com.example.d3nserver.time.repository;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.time.domain.NewsReadingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface NewsReadingTimeRepository extends JpaRepository<NewsReadingTime, Long> {
    public Optional<NewsReadingTime> findByUserIdAndNewsId(String userId, Long newsId);
    @Query("SELECT nrt FROM NewsReadingTime nrt JOIN FETCH nrt.news WHERE nrt.userId = :userId ORDER BY nrt.createdAt DESC")
    List<NewsReadingTime> findTop10ByUserIdOrderByCreatedAtDesc(@Param("userId") String userId);

}
