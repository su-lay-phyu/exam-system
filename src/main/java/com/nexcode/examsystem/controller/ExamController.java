package com.nexcode.examsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.requests.QuestionRequest;
import com.nexcode.examsystem.model.responses.ApiResponse;
import com.nexcode.examsystem.model.responses.ExamResponse;
import com.nexcode.examsystem.model.responses.QuestionAnswerResponse;
import com.nexcode.examsystem.service.ExamService;
@RestController
@RequestMapping("/api/exam")
public class ExamController {
	
	private final ExamService examService;
	private final ExamMapper examMapper;
	private final QuestionMapper questionMapper;
	
	public ExamController(ExamService examService, ExamMapper examMapper, QuestionMapper questionMapper) {
		this.examService = examService;
		this.examMapper = examMapper;
		this.questionMapper = questionMapper;
	}
	@GetMapping
	public List<ExamResponse> getAllExam()
	{
		List<ExamDto>dtos=examService.getAllExam();
		return examMapper.toResponseList(dtos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getExamById(@PathVariable Long id)
	{
		ExamDto dto=examService.findExamById(id);
		return new ResponseEntity<>(examMapper.toResponse(dto),HttpStatus.OK);
	}
	@GetMapping("/{id}/questions")
	public List<QuestionAnswerResponse> getAllQuestionsWithAnswerById(@PathVariable Long id)
	{
		List<QuestionDto>dtos=examService.getAllQuestionById(id);
		return questionMapper.toQuestionResponseList(dtos);
	}
	@PostMapping
	public ResponseEntity<?> createExamWithQuestions(@RequestBody ExamRequest request)
	{
		examService.createExam(request);
		return new ResponseEntity<>(new ApiResponse(true,"exam created successfully"), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?>updateExam(@PathVariable Long id,@RequestBody ExamRequest request)
	{
		examService.updateExam(id, request);
		return new ResponseEntity<>(new ApiResponse(true,"exam updated successfully"), HttpStatus.OK);
	}
	@PutMapping("/{id}/questions")
	public ResponseEntity<?>updateExamQuestions(@PathVariable Long id,@RequestBody List<QuestionRequest> request)
	{
		examService.updateQuestionWithExamId(id, request);
		return new ResponseEntity<>(new ApiResponse(true,"exam questions updated."), HttpStatus.OK);
	}
	@PutMapping("/{id}/publish")
	public ResponseEntity<?>publishExam(@PathVariable Long id,@RequestBody ExamPublishedRequest request)
	{
		boolean isPublished=examService.setExamPublished(id,request);
		System.out.println("isPublished "+isPublished);
		if(isPublished)
		{
			return new ResponseEntity<>(new ApiResponse(true,"Set published successfully"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse(true,"Set unpublished successfully"), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteExam(@PathVariable Long id)
	{
		examService.deleteExam(id);
		return new ResponseEntity<>(new ApiResponse(true,"exam deleted successfully"), HttpStatus.OK);
	}
}
