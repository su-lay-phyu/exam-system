package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.requests.QuestionRequest;

public interface ExamService {
	
	
	public List<ExamDto>getAllExam();
	public ExamDto createExam(ExamRequest request);
	public void updateExam(Long id,ExamRequest request);
	public void updateQuestionWithExamId(Long id,List<QuestionRequest> request);
	public List<QuestionDto>getRandomQuestionsForExam(ExamDto dto);
	public ExamDto findExamById(Long id);
	public boolean setExamPublished(Long id, ExamPublishedRequest request);
	public List<QuestionDto> getAllQuestionById(Long id);
	public void deleteExam(Long id);
}
