package com.nexcode.examsystem.mapper.impl;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.UserAnswerMapper;
import com.nexcode.examsystem.mapper.UserExamMapper;
import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.entities.UserExam;
import com.nexcode.examsystem.model.responses.UserExamResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class UserExamMapperImpl implements UserExamMapper{

	private final UserAnswerMapper userAnswerMapper;
	private final ExamMapper examMapper;
	@Override
	public UserExamDto toDto(UserExam userExam) {
		UserExamDto dto=new UserExamDto();
		dto.setId(userExam.getId());
		dto.setObtainedResult(userExam.getObtainedResult());
		dto.setSubmittedTime(userExam.getSubmittedTime());
		dto.setIsPassFail(userExam.getIsPassFail());
		dto.setUserAnswers(userAnswerMapper.toDtoList(userExam.getUserAnswers()));
		dto.setExam(examMapper.toDto(userExam.getExam()));
		return dto;
	}
	@Override
	public UserExamResponse toResponse(UserExamDto dto) {
		UserExamResponse response=new UserExamResponse();
		response.setId(dto.getId());
		response.setObtainedResult(dto.getObtainedResult());
		response.setIsPassFail(dto.getIsPassFail());
		response.setExamResponse(examMapper.toResponse(dto.getExam()));
		response.setUserAnswerResponse(userAnswerMapper.toResponseList(dto.getUserAnswers()));
		return response;
	}
	

}
