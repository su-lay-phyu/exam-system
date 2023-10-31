package com.nexcode.examsystem.mapper.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.CourseMapper;
import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.LevelMapper;
import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.responses.ExamAllResponse;
import com.nexcode.examsystem.model.responses.ExamOnlyResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ExamMapperImpl implements ExamMapper {

	private final LevelMapper levelMapper;

	private final CourseMapper courseMapper;

	private final QuestionMapper questionMapper;

	@Override
	public ExamDto toDto(Exam exam) {
		if (exam == null) {
			return null;
		}
		ExamDto dto = new ExamDto();
		dto.setId(exam.getId());
		dto.setName(exam.getName());
		dto.setDescription(exam.getDescription());
		dto.setExamDurationMinute(exam.getExamdurationMinutes());
		dto.setExamTotalMark(exam.getExamTotalMark());
		dto.setNoOfQuestion(exam.getNoOfQuestion());
		Date date = exam.getExamPublishDate();
		if (date == null) {
			dto.setPublishedDate(null);
			;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = dateFormat.format(date);
			dto.setPublishedDate(formattedDate);
		}
		dto.setLevel(levelMapper.toDto(exam.getLevel()));
		dto.setCourse(courseMapper.toDto(exam.getCourse()));
		dto.setQuestions(questionMapper.toDtoList(exam.getQuestions()));
		return dto;
	}

	@Override
	public List<ExamDto> toDtoList(List<Exam> exams) {
		if (exams == null) {
			return null;
		}
		return exams.stream().map(e -> toDto(e)).collect(Collectors.toList());
	}

	@Override
	public ExamAllResponse toResponse(ExamDto dto) {
		ExamAllResponse response = new ExamAllResponse();
		response.setId(dto.getId());
		response.setName(dto.getName());
		response.setDescription(dto.getDescription());
		response.setPublishedDate(dto.getPublishedDate());
		response.setExamTotalMark(dto.getExamTotalMark());
		response.setExamDurationMinute(dto.getExamDurationMinute());
		response.setNoOfQuestion(dto.getNoOfQuestion());
		response.setCategory(courseMapper.toResponse(dto.getCourse()));
		response.setLevel(levelMapper.toResponse(dto.getLevel()));
		response.setQuestions(questionMapper.toResponseList(dto.getQuestions()));
		return response;
	}

	@Override
	public List<ExamAllResponse> toResponseList(List<ExamDto> dtos) {
		return dtos.stream().map(e -> toResponse(e)).collect(Collectors.toList());
	}

	@Override
	public ExamOnlyResponse toExamOnlyResponse(ExamDto dto) {
		ExamOnlyResponse response = new ExamOnlyResponse();
		response.setId(dto.getId());
		response.setName(dto.getName());
		response.setDescription(dto.getDescription());
		response.setPublishedDate(dto.getPublishedDate());
		response.setExamTotalMark(dto.getExamTotalMark());
		response.setExamDurationMinute(dto.getExamDurationMinute());
		response.setIsPublished(dto.getIsPublished());
		response.setNoOfQuestion(dto.getNoOfQuestion());
		response.setCategory(courseMapper.toResponse(dto.getCourse()));
		response.setLevel(levelMapper.toResponse(dto.getLevel()));
		return response;
	}

	@Override
	public List<ExamOnlyResponse> toExamOnlyResponseList(List<ExamDto> dtos) {
		return dtos.stream().map(e->toExamOnlyResponse(e)).collect(Collectors.toList());
	}

}