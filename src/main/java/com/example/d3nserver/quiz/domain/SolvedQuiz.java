package com.example.d3nserver.quiz.domain;

import com.example.d3nserver.common.base.BaseEntity;
import com.example.d3nserver.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SolvedQuiz extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Long quizId;
    private Integer selectedAnswer;
    private Integer quizAnswer;

    public SolvedQuiz(User user, Long quizId, Integer selectedAnswer, Integer quizAnswer){
        this.user = user;
        this.quizId = quizId;
        this.selectedAnswer = selectedAnswer;
        this.quizAnswer = quizAnswer;
    }

    public boolean getQuizResult(){
        return selectedAnswer.intValue() == quizAnswer.intValue();
    }
}
