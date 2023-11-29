package com.nexcode.examsystem.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description", columnDefinition = "text")
	private String description;
	
	@Column(name="exam_publish_date")
	private Date examPublishDate;
	
    @Column(name = "is_published")
    private boolean isPublished;

	@Column(name="exam_duration_minute")
	private Integer examdurationMinutes;
	
	@Column(name="exam_total_mark")
	private Integer examTotalMark;
	
	@Column(name="no_of_question_to_generate")
	private Integer numberOfQuestionsToGenerate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") 
	private Course course;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id") 
    private Level level;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private List<Question> questions;

	public Exam() {
		
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

	public Date getExamPublishDate() {
		return examPublishDate;
	}

	public void setExamPublishDate(Date examPublishDate) {
		this.examPublishDate = examPublishDate;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getExamdurationMinutes() {
		return examdurationMinutes;
	}

	public void setExamdurationMinutes(Integer examdurationMinutes) {
		this.examdurationMinutes = examdurationMinutes;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
