package com.nexcode.examsystem.model.dtos;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionDto {
	
    private Long id;
    private String question;
    private Long examId;
    private List<AnswerDto>answerDtos;
}
