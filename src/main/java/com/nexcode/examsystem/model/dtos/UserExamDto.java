package com.nexcode.examsystem.model.dtos;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class UserExamDto {

    private Long id;
    private Integer obtainedResult;
    private Date submittedTime;
    private boolean isPass;
    private boolean isActive;
    private UserDto user;
    private ExamDto exam;
    private List<UserAnswerDto> userAnswers;
}
