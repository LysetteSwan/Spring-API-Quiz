package com.cooksys.quiz_api.dtos;

import com.cooksys.quiz_api.entities.Question;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class AnswerRequestDto {

	 private String text;

	 private boolean correct = false;
	  
	 private Question question;

}
