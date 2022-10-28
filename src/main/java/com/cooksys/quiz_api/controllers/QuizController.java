package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.services.QuizService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }
  
  @GetMapping("/{id}/random")
 public QuestionResponseDto getRandomQuestionById(@PathVariable Long id) {
	  return quizService.getRandomQuestionById(id);
  }
  
  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
	  return quizService.createQuiz(quizRequestDto);
  }
  
  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable Long id) {
	  return quizService.deleteQuiz(id);
  }
  
  @PatchMapping ("/{id}/rename/{newName}")
  public QuizResponseDto updateQuizName(@PathVariable Long id, @PathVariable String newName ) {
	  return quizService.updateQuizName(id, newName);
  }
  
  
  @PatchMapping ("{id}/add")
  public QuizResponseDto updateQuizQuestion(@PathVariable Long id, @RequestBody Question question) {
	  return quizService.updateQuizQuestion(id, question);
  }
	  
  
  @DeleteMapping("{id}/delete/{questionID}")
  public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable Long id,@PathVariable Long questionID) {
	  return quizService.deleteQuestionFromQuiz(id, questionID);
  }
  

}
