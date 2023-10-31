package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexcode.examsystem.model.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

//	 @Query("SELECT q FROM Question q WHERE q.exam.id = :examId ORDER BY FUNCTION('RAND') LIMIT :numberOfQuestions")
//	 List<Question> findRandomQuestions(@Param("examId") Long examId, @Param("numberOfQuestions") Integer numberOfQuestions);
}
