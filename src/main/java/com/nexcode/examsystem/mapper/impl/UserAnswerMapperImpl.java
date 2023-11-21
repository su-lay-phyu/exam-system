package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.mapper.UserAnswerMapper;
import com.nexcode.examsystem.model.dtos.UserAnswerDto;
import com.nexcode.examsystem.model.entities.UserAnswer;
import com.nexcode.examsystem.model.responses.UserAnswerResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class UserAnswerMapperImpl implements UserAnswerMapper{

	private final QuestionMapper questionMapper;
	@Override
	public UserAnswerDto toDto(UserAnswer userAnswer) {
		UserAnswerDto dto=new UserAnswerDto();
		dto.setId(userAnswer.getId());
		dto.setQuestion(questionMapper.toDto(userAnswer.getQuestion()));
		dto.setSelectedAnswer(userAnswer.getSelectedAnswer());
		dto.setSelectedAnswerCorrect(userAnswer.isSelectedAnswerCorrect());
		return dto;
	}

	@Override
	public List<UserAnswerDto> toDtoList(List<UserAnswer> answers) {
		return answers.stream().map(a->toDto(a)).collect(Collectors.toList());
	}

	@Override
	public UserAnswerResponse toResponse(UserAnswerDto dto) {
		UserAnswerResponse response=new UserAnswerResponse();
		response.setId(dto.getId());
		response.setQuestionResponse(questionMapper.toResponse(dto.getQuestion()));
		response.setSelectedAnswer(dto.getSelectedAnswer());
		response.setIsSelectedAnswerCorrect(dto.isSelectedAnswerCorrect());
		return response;
	}

	@Override
	public List<UserAnswerResponse> toResponseList(List<UserAnswerDto> dtos) {
		return dtos.stream().map(ua->toResponse(ua)).collect(Collectors.toList());
	}
	

}
