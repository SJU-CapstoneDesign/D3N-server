package com.example.d3nserver.quiz.domain;

import com.example.d3nserver.common.base.BaseEntity;
import com.example.d3nserver.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Lazy;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SolvedQuiz extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    private Integer selectedAnswer;

    public SolvedQuiz(User user, Quiz quiz, Integer selectedAnswer){
        this.user = user;
        this.quiz = quiz;
        this.selectedAnswer = selectedAnswer;
    }

    public boolean getQuizResult(){
        return selectedAnswer.equals(quiz.getAnswer());
    }
}
