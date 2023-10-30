package com.example.d3nserver.quiz.service;

import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.quiz.dto.QuizResponseDto;
import com.example.d3nserver.quiz.dto.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.repository.QuizRepository;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserService userService;
    public List<QuizResponseDto> getQuizList(Long newsId){
        List<Quiz> quizList = quizRepository.findAllByNewsId(newsId);
        return quizList.stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }

    public List<SolvedQuiz> saveSolvedQuizList(String userId, List<SolvedQuizRequestDto> solvedQuizRequestDtoList){
        User findUser = userService.findById(userId).get();
        for(SolvedQuizRequestDto solvedQuizRequestDto : solvedQuizRequestDtoList){
            findUser.getSolvedQuizList().add(new SolvedQuiz(solvedQuizRequestDto.getQuizId(), solvedQuizRequestDto.getSelectedAnswer(), solvedQuizRequestDto.getQuizAnswer()));
        }
        userService.save(findUser);
        return findUser.getSolvedQuizList();
    }
}
