package com.nexcode.examsystem.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_answers")
public class UserAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(name = "selected_answer")
	private String selectedAnswer;

	@Column(name = "is_selected_answer_correct")
	private boolean isSelectedAnswerCorrect;

	//@JsonBackReference  
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_exam_id")
    private UserExam userExam;

	public UserAnswer() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public boolean isSelectedAnswerCorrect() {
		return isSelectedAnswerCorrect;
	}

	public void setSelectedAnswerCorrect(boolean isSelectedAnswerCorrect) {
		this.isSelectedAnswerCorrect = isSelectedAnswerCorrect;
	}

	public UserExam getUserExam() {
		return userExam;
	}

	public void setUserExam(UserExam userExam) {
		this.userExam = userExam;
	}
	
}
