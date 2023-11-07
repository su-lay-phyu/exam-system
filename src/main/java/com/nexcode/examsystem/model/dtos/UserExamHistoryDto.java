package com.nexcode.examsystem.model.dtos;

import java.util.Collection;
import java.util.List;

import com.nexcode.examsystem.model.entities.UserAnswer;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserExamHistoryDto {
	
    private Integer obtainedResult;
    private Boolean isPassFail;
    private Long examId; 
    private String examName;
    private String examDescription;  
    private Integer examTotalMark;
   // private String level;
    private String course;
    private List<UserAnswer> userAnswers;
//	public UserExamHistoryDto(int obtainedResult, boolean isPassFail, long examId, String examName,
//			String examDescription, int examTotalMark, String level, String course, Collection<UserAnswer> userAnswers) {
//		super();
//		this.obtainedResult = obtainedResult;
//		this.isPassFail = isPassFail;
//		this.examId = examId;
//		this.examName = examName;
//		this.examDescription = examDescription;
//		this.examTotalMark = examTotalMark;
//		this.level = level;
//		this.course = course;
//		this.userAnswers = (List<UserAnswer>) userAnswers;
//	}
    public UserExamHistoryDto(int obtainedResult, boolean isPassFail, long examId, String examName,
			String examDescription, int examTotalMark, String course, Collection<UserAnswer> userAnswers) {
		super();
		this.obtainedResult = obtainedResult;
		this.isPassFail = isPassFail;
		this.examId = examId;
		this.examName = examName;
		this.examDescription = examDescription;
		this.examTotalMark = examTotalMark;
		this.course = course;
		this.userAnswers = (List<UserAnswer>) userAnswers;
	}
    
}


