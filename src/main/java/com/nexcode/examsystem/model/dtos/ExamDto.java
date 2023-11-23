package com.nexcode.examsystem.model.dtos;

import java.util.List;

public class ExamDto {
	private Long id;
	private String name;
	private String description;
	private Integer examDurationMinute;
	private Integer examTotalMark;
	private Integer numberOfQuestionsToGenerate;
	private String publishedDate;
	private boolean isPublished;
	private Long courseId;
	private Long levelId;
	private CourseDto course;
	private LevelDto level;
	private List<QuestionDto>questions;
	
	public ExamDto() {
		
	}
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
	public Integer getExamTotalMark() {
		return examTotalMark;
	}
	public void setExamTotalMark(Integer examTotalMark) {
		this.examTotalMark = examTotalMark;
	}
	public Integer getNumberOfQuestionsToGenerate() {
		return numberOfQuestionsToGenerate;
	}
	public void setNumberOfQuestionsToGenerate(Integer numberOfQuestionsToGenerate) {
		this.numberOfQuestionsToGenerate = numberOfQuestionsToGenerate;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public CourseDto getCourse() {
		return course;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	public LevelDto getLevel() {
		return level;
	}
	public void setLevel(LevelDto level) {
		this.level = level;
	}
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}
	
	
}
