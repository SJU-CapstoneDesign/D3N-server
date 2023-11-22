package com.example.d3nserver.time.domain;

import com.example.d3nserver.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class QuizSolvingTime extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long quizId;
    private String userId;
    private Integer secondTime;

    public QuizSolvingTime(String userId, Long quizId){
        this.userId = userId;
        this.quizId = quizId;
        this.secondTime = 0;
    }

    public void updateSolvingTime(int secondTime){
        this.secondTime = secondTime;
    }



}
