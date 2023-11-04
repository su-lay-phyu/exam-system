package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.AnswerMapper;
import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.entities.Question;
import com.nexcode.examsystem.model.responses.QuestionAnswerResponse;
import com.nexcode.examsystem.model.responses.QuestionResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class QuestionMapperImpl implements QuestionMapper{

	private final AnswerMapper answerMapper;
	@Override
	public QuestionDto toDto(Question q) {
		QuestionDto dto=new QuestionDto();
		dto.setId(q.getId());
		dto.setQuestion(q.getQuestion());
		dto.setAnswerDtos(answerMapper.toDtoList(q.getAnswers()));
		return dto;
	}

	@Override
	public List<QuestionDto> toDtoList(List<Question> questions) {
		return questions.stream().map(q->toDto(q)).collect(Collectors.toList());
	}

	@Override
	public QuestionResponse toResponse(QuestionDto dto) {
		QuestionResponse response=new QuestionResponse();
		response.setId(dto.getId());
		response.setQuestion(dto.getQuestion());
		response.setAnswers(answerMapper.toResponseList(dto.getAnswerDtos()));
		return response;
	}

	@Override
	public List<QuestionResponse> toResponseList(List<QuestionDto> dtos) {
		return dtos.stream().map(q->toResponse(q)).collect(Collectors.toList());
	}

	@Override
	public QuestionAnswerResponse toQuestionResponse(QuestionDto dto) {
		QuestionAnswerResponse response=new QuestionAnswerResponse();
		response.setId(dto.getId());
		response.setQuestion(dto.getQuestion());
		response.setAnswers(answerMapper.toAnswerResponseList(dto.getAnswerDtos()));
		return response;
	}

	@Override
	public List<QuestionAnswerResponse> toQuestionResponseList(List<QuestionDto> dtos) {
		return dtos.stream().map(q->toQuestionResponse(q)).collect(Collectors.toList());
	}

}
