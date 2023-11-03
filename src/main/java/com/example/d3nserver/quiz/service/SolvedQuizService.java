package com.example.d3nserver.quiz.service;

import com.example.d3nserver.common.exception.CustomException;
import com.example.d3nserver.common.exception.ErrorCode;
import com.example.d3nserver.quiz.domain.Quiz;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import com.example.d3nserver.quiz.dto.SolvedQuizRequestDto;
import com.example.d3nserver.quiz.dto.SolvedQuizResponseDto;
import com.example.d3nserver.quiz.repository.QuizRepository;
import com.example.d3nserver.quiz.repository.SolvedQuizRepository;
import com.example.d3nserver.user.domain.User;
import com.example.d3nserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolvedQuizService {
    private final SolvedQuizRepository solvedQuizRepository;
    private final QuizRepository quizRepository;

    public List<SolvedQuizResponseDto> saveSolvedQuizList(User user, List<SolvedQuizRequestDto> solvedQuizRequestDtoList) throws CustomException {
        List<SolvedQuiz> solvedQuizList = new ArrayList<>();
        for(SolvedQuizRequestDto solvedQuizRequestDto : solvedQuizRequestDtoList){
            Quiz quiz = quizRepository.findById(solvedQuizRequestDto.getQuizId()).orElseThrow(() -> new CustomException(ErrorCode.QUIZ_NOT_FOUND));
            SolvedQuiz solvedQuiz = new SolvedQuiz(user,solvedQuizRequestDto.getQuizId(), solvedQuizRequestDto.getSelectedAnswer(), quiz.getAnswer());
            solvedQuizList.add(solvedQuiz);
            solvedQuizRepository.save(solvedQuiz);
        }
        return solvedQuizList.stream().map(SolvedQuizResponseDto::new).collect(Collectors.toList());
    }

    public List<SolvedQuizResponseDto> getUserSolvedQuizList(User user){
        return solvedQuizRepository.findAllByUser(user).stream().map(SolvedQuizResponseDto::new).collect(Collectors.toList());
    }

    public List<SolvedQuizResponseDto> getUserIncorrectQuizList(User user){
        return solvedQuizRepository.findAllByUser(user).stream().filter(solvedQuiz -> !solvedQuiz.getQuizResult()).map(SolvedQuizResponseDto::new).collect(Collectors.toList());
    }
}
