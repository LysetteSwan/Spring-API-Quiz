package com.cooksys.quiz_api.dtos;

import java.util.List;

import com.cooksys.quiz_api.entities.Question;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class QuizRequestDto {
	
	private String name;

	 private List<QuestionRequestDto> questions;
	
}
