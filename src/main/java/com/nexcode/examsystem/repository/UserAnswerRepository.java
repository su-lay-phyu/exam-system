package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexcode.examsystem.model.entities.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long>{

}
