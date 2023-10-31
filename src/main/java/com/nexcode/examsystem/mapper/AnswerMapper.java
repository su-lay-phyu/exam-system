package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.AnswerDto;
import com.nexcode.examsystem.model.entities.Answer;
import com.nexcode.examsystem.model.responses.AnswerResponse;
import com.nexcode.examsystem.model.responses.CorrectAnswerResponse;

public interface AnswerMapper {

	public AnswerDto toDto(Answer answer);
	public List<AnswerDto>toDtoList(List<Answer>answers);
	public AnswerResponse toResponse(AnswerDto dto);
	public List<AnswerResponse>toResponseList(List<AnswerDto>dto);
	public CorrectAnswerResponse toAnswerResponse(AnswerDto dto);
	public List<CorrectAnswerResponse>toAnswerResponseList(List<AnswerDto>dtos);
}
