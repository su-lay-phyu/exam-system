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
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.responses.ApiResponse;
import com.nexcode.examsystem.model.responses.ExamAllResponse;
import com.nexcode.examsystem.model.responses.ExamOnlyResponse;
import com.nexcode.examsystem.model.responses.QuestionAnswerResponse;
import com.nexcode.examsystem.model.responses.QuestionResponse;
import com.nexcode.examsystem.security.CurrentUser;
import com.nexcode.examsystem.security.UserPrincipal;
import com.nexcode.examsystem.service.ExamService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {
	
	private final ExamService examService;
	
	private final ExamMapper examMapper;
	private final QuestionMapper questionMapper;
	
	@GetMapping()
	public List<ExamOnlyResponse> getAllExamOnly()
	{
		List<ExamDto>dtos=examService.getAllExamDetails();
		return examMapper.toExamOnlyResponseList(dtos);
	}
	@GetMapping("/details")
	public List<ExamAllResponse> getAllExam()
	{
		List<ExamDto>dtos=examService.getAllExamDetails();
		return examMapper.toResponseList(dtos);
	}
	@GetMapping("/{id}")
	public List<QuestionAnswerResponse> getAllQuestionsWithAnswerById(@PathVariable Long id)
	{
		List<QuestionDto>dtos=examService.getAllQuestionById(id);
		return questionMapper.toQuestionResponseList(dtos);
	}
	@PostMapping
	public ResponseEntity<?> createExamWithQuestions(@RequestBody ExamRequest request)
	{
		try {
	        boolean created = examService.createExamWithQuestions(request);
	        if (created) {
	            return new ResponseEntity<>(new ApiResponse(true, "Exam and questions added successfully"), HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(new ApiResponse(false, "Failed to add exam and questions"), HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(new ApiResponse(false, "An error occurred while processing the request"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PutMapping("/{id}")
	public ResponseEntity<?>updateExam(@PathVariable Long id,@RequestBody ExamRequest request)
	{
		ExamDto foundedExam=examService.findExamById(id);
		if(foundedExam==null)
		{
			throw new BadRequestException("Exam not found");
		}
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
		ExamDto foundedExam=examService.findExamById(id);
		if(foundedExam==null)
		{
			return new ResponseEntity<>(new ApiResponse(false,"Exam not found"),HttpStatus.NOT_FOUND);
		}
		boolean isPublished =examService.setExamPublished(id,request);
		return new ResponseEntity<>(new ApiResponse(isPublished,"Set published successfully"),HttpStatus.OK);
	}
	@GetMapping("/start/{id}")
    public List<QuestionResponse> startExam(@CurrentUser UserPrincipal currentUser,@PathVariable Long id) 
    {
		ExamDto foundedExam=examService.findExamById(id);
		if(foundedExam==null)
		{
			throw new BadRequestException("Exam not found");
		}
		List<QuestionDto>dtos=examService.getRandomQuestionsForExam(foundedExam);
		return questionMapper.toResponseList(dtos);
	}
}
