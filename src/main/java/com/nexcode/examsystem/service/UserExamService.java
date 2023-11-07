package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.UserExamHistoryDto;
import com.nexcode.examsystem.model.requests.UserAnswerRequest;

public interface UserExamService {

	public boolean createUserExam(Long userId,Long examId);
	public Long findUserExamByUserAndExam(Long userId,Long examId,List<UserAnswerRequest>userAnswer);
	public UserExamHistoryDto getExamHistoryByUser(Long userId,Long examId);
	//public List<UserExamHistoryDto> getExamHistoryByUser(Long userId,Long examId);
}
