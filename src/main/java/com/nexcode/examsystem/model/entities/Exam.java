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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
    @Column(name = "is_published", columnDefinition = "boolean default false")
    private Boolean isPublished;

	@Column(name="exam_duration_minute")
	private Integer examdurationMinutes;
	
	@Column(name="exam_total_mark")
	private Integer examTotalMark;
	
	@Column(name="no_of_question")
	private Integer noOfQuestion;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") 
	private Course course;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id") 
    private Level level;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "exam_id")
	private List<Question> questions;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;
}
