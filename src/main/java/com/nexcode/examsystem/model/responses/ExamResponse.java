package com.nexcode.examsystem.model.responses;

public class ExamResponse {
	
	private Long id;
	private String name;
	private String description;
	private Integer examDurationMinute;
	private String publishedDate;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private CourseResponse category;
	private LevelResponse level;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExamDurationMinute() {
		return examDurationMinute;
	}
	public void setExamDurationMinute(Integer examDurationMinute) {
		this.examDurationMinute = examDurationMinute;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Integer getExamTotalMark() {
		return examTotalMark;
	}
	public void setExamTotalMark(Integer examTotalMark) {
		this.examTotalMark = examTotalMark;
	}
	public Integer getNoOfQuestion() {
		return noOfQuestion;
	}
	public void setNoOfQuestion(Integer noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}
	public CourseResponse getCategory() {
		return category;
	}
	public void setCategory(CourseResponse category) {
		this.category = category;
	}
	public LevelResponse getLevel() {
		return level;
	}
	public void setLevel(LevelResponse level) {
		this.level = level;
	}
}
