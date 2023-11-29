package com.nexcode.examsystem.model.responses;

import java.util.List;

public class TakeExamResponse {
	private ExamResponse exam;
	private List<QuestionResponse>questions;
	public TakeExamResponse()
	{
		
	}
	public ExamResponse getExam() {
		return exam;
	}
	public void setExam(ExamResponse exam) {
		this.exam = exam;
	}
	public List<QuestionResponse> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionResponse> questions) {
		this.questions = questions;
	}
	
	
}
