package com.nexcode.examsystem.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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
    
    @Column(name="is_selected_answer_correct")
    private Boolean isSelectedAnswerCorrect;

}
