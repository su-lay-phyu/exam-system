package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.projections.UserExamHistoryProjection;
import com.nexcode.examsystem.model.requests.UserAnswerRequest;

public interface UserExamService {

	public void createUserExam(Long userId,Long examId);
	public UserExamDto findUserExamByUserAndExam(Long userId,Long examId,List<UserAnswerRequest>userAnswer);
	public List<UserExamHistoryProjection>getAllExamHistoryByUserId(String email);
	public UserExamDto getEachExamAllHistory(String email,Long examId);
}
