package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.responses.ExamAllResponse;
import com.nexcode.examsystem.model.responses.ExamOnlyResponse;

public interface ExamMapper {

	public ExamDto toDto(Exam exam);
	public List<ExamDto>toDtoList(List<Exam>exams);
	public ExamAllResponse toResponse(ExamDto dto);
	public List<ExamAllResponse>toResponseList(List<ExamDto>dtos);
	public ExamOnlyResponse toExamOnlyResponse(ExamDto dto);
	public List<ExamOnlyResponse>toExamOnlyResponseList(List<ExamDto>dtos);
}
