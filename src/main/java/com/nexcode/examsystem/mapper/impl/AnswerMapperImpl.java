package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.AnswerMapper;
import com.nexcode.examsystem.model.dtos.AnswerDto;
import com.nexcode.examsystem.model.entities.Answer;
import com.nexcode.examsystem.model.responses.AnswerResponse;
import com.nexcode.examsystem.model.responses.CorrectAnswerResponse;

@Component
public class AnswerMapperImpl implements AnswerMapper{
	@Override
	public AnswerDto toDto(Answer answer) {
		AnswerDto dto=new AnswerDto();
		dto.setId(answer.getId());
		dto.setAnswer(answer.getAnswer());
		dto.setCorrectAnswer(answer.isCorrectAnswer());
		return dto;
	}

	@Override
	public List<AnswerDto> toDtoList(List<Answer> answers) {
		return answers.stream().map(a->toDto(a)).collect(Collectors.toList());
	}

	@Override
	public AnswerResponse toResponse(AnswerDto dto) {
		AnswerResponse response=new AnswerResponse();
		response.setAnswer(dto.getAnswer());
		return response;
	}

	@Override
	public List<AnswerResponse> toResponseList(List<AnswerDto> dtos) {
		return dtos.stream().map(a->toResponse(a)).collect(Collectors.toList());
	}

	@Override
	public CorrectAnswerResponse toAnswerResponse(AnswerDto dto) {
		CorrectAnswerResponse response=new CorrectAnswerResponse();
		response.setAnswer(dto.getAnswer());
		response.setCorrectAnswer(dto.isCorrectAnswer());
		return response;
	}

	@Override
	public List<CorrectAnswerResponse> toAnswerResponseList(List<AnswerDto> dtos) {
		return dtos.stream().map(d->toAnswerResponse(d)).collect(Collectors.toList());
	}

}
