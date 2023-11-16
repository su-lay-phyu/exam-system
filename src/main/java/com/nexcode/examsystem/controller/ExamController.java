package com.nexcode.examsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.responses.ApiResponse;
import com.nexcode.examsystem.model.responses.ExamResponse;
import com.nexcode.examsystem.model.responses.QuestionAnswerResponse;
import com.nexcode.examsystem.service.ExamService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {
	
	private final ExamService examService;
	
	private final ExamMapper examMapper;
	private final QuestionMapper questionMapper;
	@GetMapping
	public List<ExamResponse> getAllExam()
	{
		List<ExamDto>dtos=examService.getAllExam();
		return examMapper.toResponseList(dtos);
	}
	//this one will called at Admin side
	@GetMapping("/{id}")
	public List<QuestionAnswerResponse> getAllQuestionsWithAnswerById(@PathVariable Long id)
	{
		List<QuestionDto>dtos=examService.getAllQuestionById(id);
		return questionMapper.toQuestionResponseList(dtos);
	}
	@PostMapping
	public ResponseEntity<?> createExamWithQuestions(@RequestBody ExamRequest request)
	{
		boolean isAdded = examService.createExamWithQuestions(request);
		if(isAdded)
		{
			return new ResponseEntity<>(new ApiResponse(isAdded, "Exam and questions added successfully"), HttpStatus.CREATED);
		}
		throw new AppException("An error occurred while processing the update exam");
	}
	@PutMapping("/{id}")
	public ResponseEntity<?>updateExam(@PathVariable Long id,@RequestBody ExamRequest request)
	{
		boolean updated=examService.updateExam(id, request);
		if(updated)
		{
			return new ResponseEntity<>(new ApiResponse(true, "Exam and questions updated successfully"), HttpStatus.OK);
		}
		throw new AppException("An error occurred while processing the update exam");
	}
	@PutMapping("/{id}/publish")
	public ResponseEntity<?>publishExam(@PathVariable Long id,@RequestBody ExamPublishedRequest request)
	{
		boolean isPublished =examService.setExamPublished(id,request);
		return new ResponseEntity<>(new ApiResponse(isPublished,"Set published successfully"),HttpStatus.OK);
	}
	
}
