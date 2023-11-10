package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.examsystem.model.entities.UserExam;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

	@Query("SELECT ue FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.id = :examId")
	public UserExam findByUserExam(Long userId, Long examId);

//	@Query("SELECT userExam, ua FROM UserExam userExam LEFT JOIN FETCH userExam.userAnswers ua WHERE userExam.id = :userExamId")
//	UserExamHistoryProjection findUserExamHistory(@Param("userExamId") Long userExamId);

	@Query("SELECT ue FROM UserExam ue WHERE ue.id = :userExamId")
	UserExam findUserExam(@Param("userExamId") Long userExamId);

}
