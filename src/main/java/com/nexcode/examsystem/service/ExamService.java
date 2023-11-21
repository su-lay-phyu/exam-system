package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;

public interface ExamService {
	
	
	public List<ExamDto>getAllExam();
	public ExamDto createExamWithQuestions(ExamRequest request);
	public void updateExam(Long id,ExamRequest request);
	public List<QuestionDto>getRandomQuestionsForExam(ExamDto dto);
	public ExamDto findExamById(Long id);
	public void setExamPublished(Long id, ExamPublishedRequest request);
	public List<QuestionDto> getAllQuestionById(Long id);
	public void deleteExam(Long id);
}
