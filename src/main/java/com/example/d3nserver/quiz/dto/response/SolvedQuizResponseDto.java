package com.example.d3nserver.quiz.dto.response;
import com.example.d3nserver.news.dto.NewsResponseDto;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import lombok.Data;
import java.util.List;

@Data
public class SolvedQuizResponseDto {
    private Long id;
    private String question;
    private List<String> choiceList;
    private Integer answer;
    private String reason;
    private Integer selectedAnswer;
    private NewsResponseDto news;

    public SolvedQuizResponseDto(SolvedQuiz solvedQuiz) {
        id = solvedQuiz.getId();
        question = solvedQuiz.getQuiz().getQuestion();
        choiceList = solvedQuiz.getQuiz().getChoiceList();
        answer = solvedQuiz.getQuiz().getAnswer();
        reason = solvedQuiz.getQuiz().getReason();
        selectedAnswer = solvedQuiz.getSelectedAnswer();
        news = new NewsResponseDto(solvedQuiz.getQuiz().getNews());
    }
}
