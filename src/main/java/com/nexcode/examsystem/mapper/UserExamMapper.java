package com.nexcode.examsystem.mapper;

import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.entities.UserExam;
import com.nexcode.examsystem.model.responses.UserExamResponse;

public interface UserExamMapper {

	public UserExamDto toDto(UserExam userExam);
	public UserExamResponse toResponse(UserExamDto dto);
}
