package com.cooksys.quiz_api.dtos;

import java.util.List;

import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Quiz;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionRequestDto {

	private String text;
	
	private Quiz quiz;
	
	private List<AnswerRequestDto> answers;
	
}
