package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.requests.UserAnswerRequest;

public interface UserExamService {

	public boolean createUserExam(Long userId,Long examId);
	public UserExamDto findUserExamByUserAndExam(Long userId,Long xamId,List<UserAnswerRequest>userAnswer);
	public UserExamDto getExamHistory(Long userExamId);
}
