package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.entities.Question;
import com.nexcode.examsystem.model.responses.QuestionAnswerResponse;
import com.nexcode.examsystem.model.responses.QuestionResponse;

public interface QuestionMapper {

	public QuestionDto toDto(Question question);
	public List<QuestionDto> toDtoList(List<Question>questions);
	public QuestionResponse toResponse(QuestionDto dto);
	public List<QuestionResponse>toResponseList(List<QuestionDto>dtos);
	public QuestionAnswerResponse toQuestionResponse(QuestionDto dto);
	public List<QuestionAnswerResponse>toQuestionResponseList(List<QuestionDto>dtos);
}
