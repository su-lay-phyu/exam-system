package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.UserAnswerDto;
import com.nexcode.examsystem.model.entities.UserAnswer;
import com.nexcode.examsystem.model.responses.UserAnswerResponse;

public interface UserAnswerMapper {

	public UserAnswerDto toDto(UserAnswer userAnswer);
	public List<UserAnswerDto>toDtoList(List<UserAnswer>answers);
	public UserAnswerResponse toResponse(UserAnswerDto dto);
	public List<UserAnswerResponse>toResponseList(List<UserAnswerDto>dtos);
}
