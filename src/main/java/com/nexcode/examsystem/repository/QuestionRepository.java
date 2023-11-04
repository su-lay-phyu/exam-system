package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexcode.examsystem.model.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
