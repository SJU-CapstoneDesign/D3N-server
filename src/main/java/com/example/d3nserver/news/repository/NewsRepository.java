package com.example.d3nserver.news.repository;

import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.news.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n ORDER BY n.createdAt DESC LIMIT :n")
    List<News> findTopNByOrderByCreatedAtDesc(@Param("n") int cnt);
    @Query("SELECT n FROM News n WHERE n.field = :field ORDER BY n.createdAt DESC LIMIT :n")
    List<News> findTopNByField(@Param("field") Field field, @Param("n") int n);
    Page<News> findAllNewsByOrderByCreatedAtDesc(Pageable pageable);
    Page<News> findAllByFieldOrderByCreatedAtDesc(Field field, Pageable pageable);
}
