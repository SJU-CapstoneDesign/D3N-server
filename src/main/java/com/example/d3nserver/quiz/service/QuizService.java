package com.example.d3nserver.quiz.service;

import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.quiz.dto.response.QuizResponseDto;
import com.example.d3nserver.quiz.repository.QuizRepository;
import com.example.d3nserver.quiz.repository.SolvedQuizRepository;
import com.example.d3nserver.time.domain.QuizSolvingTime;
import com.example.d3nserver.time.repository.QuizSolvingTimeRepository;
import com.example.d3nserver.time.service.QuizSolvingTimeService;
import com.example.d3nserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;
    private final QuizSolvingTimeRepository quizSolvingTimeRepository;

    public List<QuizResponseDto> getQuizList(Long newsId){
        List<Quiz> quizList = quizRepository.findAllByNewsId(newsId);
        return quizList.stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }
    public List<QuizResponseDto> getQuizListByUser(User user, Long newsId){
        List<Quiz> quizList = quizRepository.findAllByNewsId(newsId);
        List<QuizResponseDto> quizResponseDtoList = new ArrayList<>();
        for (Quiz quiz: quizList){
            Optional<SolvedQuiz> solvedQuiz = solvedQuizRepository.findByUserAndQuiz(user, quiz);
            QuizResponseDto quizResponseDto = new QuizResponseDto(quiz);
            solvedQuiz.ifPresent(value -> quizResponseDto.setSelectedAnswer(value.getSelectedAnswer()));
            Optional<QuizSolvingTime> quizSolvingTime = quizSolvingTimeRepository.findByUserIdAndQuizId(user.getId(), quiz.getId());
            quizSolvingTime.ifPresent(value -> quizResponseDto.setSecondTime(value.getSecondTime()));
            quizResponseDtoList.add(quizResponseDto);
        }
        return quizResponseDtoList;
    }

}
