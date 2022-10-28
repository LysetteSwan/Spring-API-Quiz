package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	private final QuestionRepository questionRepository;
	private final QuizMapper quizMapper;
	private final QuestionMapper questionMapper;
	
	
	private Quiz getQuiz(Long id) {
		Optional<Quiz> optionalquiz = quizRepository.findById(id);
		System.out.println(id);
		return optionalquiz.get();
	}

	private Question getQuestion(Long questionId) {
		Optional<Question> optionalquestion = questionRepository.findById(questionId);
		return optionalquestion.get();
	}

	@Override
	public List<QuizResponseDto> getAllQuizzes() {
		return quizMapper.entitiesToDtos(quizRepository.findAll());
	}

	@Override
	public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
		Quiz quizToSave = quizMapper.requestDtoToEntity(quizRequestDto);

		List<Question> questionsToAdd = quizToSave.getQuestions();
		for (Question q : questionsToAdd) {
			q.setQuiz(quizToSave);
			for (Answer answer : q.getAnswers()) {
				answer.setQuestion(q);
			}
		}
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToSave));
	}

	@Override
	public QuizResponseDto deleteQuiz(Long id) {
		Quiz quizToDelete = getQuiz(id);
		quizRepository.delete(quizToDelete);
		return quizMapper.entityToDto(quizToDelete);

	}

	@Override
	public QuizResponseDto updateQuizName(Long id, String newName) {
		Quiz quizToUpdate = getQuiz(id);
		quizToUpdate.setName(newName);
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToUpdate));
	}

	@Override
	public QuizResponseDto getQuizById(Long id) {
		return quizMapper.entityToDto(getQuiz(id));
	}

	@Override
	public QuizResponseDto updateQuizQuestion(Long id, Question question) {

		Quiz quizToUpdate = getQuiz(id);
		
		List<Question> quizQuestions = quizToUpdate.getQuestions();

		quizQuestions.add(question);

		for (Question q : quizQuestions) {
			q.setQuiz(quizToUpdate);
			for (Answer answer : q.getAnswers()) {
				answer.setQuestion(q);
			}
		}

		quizToUpdate.setQuestions(quizQuestions);
		return quizMapper.entityToDto(quizRepository.saveAndFlush(quizToUpdate));
	}

	@Override
	public QuestionResponseDto deleteQuestionFromQuiz(Long id, Long questionID) {
		Quiz quizToUpdate = getQuiz(id);
		Question questionToDelete = getQuestion(questionID);
		
		
		if(!quizToUpdate.getQuestions().contains(questionToDelete)) {
		} 
		 quizToUpdate.getQuestions().remove(questionToDelete);
		
		quizRepository.saveAndFlush(quizToUpdate);
		questionRepository.delete(questionToDelete);
		
		return questionMapper.entityToDto(questionToDelete);

	}

	@Override
	public QuestionResponseDto getRandomQuestionById(Long id) {
		List<Question> questions = getQuiz(id).getQuestions();
		Random r = new Random();
		Question question = questions.get(r.nextInt(questions.size()));
		return questionMapper.entityToDto(question);
	}

}
