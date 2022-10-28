package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();
  
  QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

  QuizResponseDto getQuizById(Long id); 
  
  QuizResponseDto deleteQuiz(Long id);
  
  QuizResponseDto updateQuizName(Long id, String newName);

  QuizResponseDto updateQuizQuestion(Long id, Question question);

  QuestionResponseDto deleteQuestionFromQuiz(Long id,Long questionID);

  QuestionResponseDto getRandomQuestionById(Long id);

}
