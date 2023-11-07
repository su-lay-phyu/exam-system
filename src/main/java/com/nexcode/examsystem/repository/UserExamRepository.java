package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexcode.examsystem.model.dtos.UserExamHistoryDto;
import com.nexcode.examsystem.model.entities.UserExam;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

	@Query("SELECT ue FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.id = :examId")
	public UserExam findByUserExam(Long userId, Long examId);
	
//	@Query("SELECT new com.nexcode.examsystem.model.dtos.UserExamHistoryDto("
//		    + "ue.obtainedResult as obtainedResult, ue.isPassFail as isPassFail, "
//		    + "ue.exam.id as examId, ue.exam.name as examName, ue.exam.description as examDescription, ue.exam.examTotalMark as examTotalMark, "
//		    + "ue.exam.level.name as level, ue.exam.course.name as course, ue.userAnswers as userAnswers) "
//		    + "FROM UserExam ue "
//		    + "WHERE ue.user.id = :userId AND ue.exam.id = :examId")
//	public UserExamHistoryDto findUserExamHistory(Long userId, Long examId);
	@Query("SELECT new com.nexcode.examsystem.model.dtos.UserExamHistoryDto("
		    + "ue.obtainedResult as obtainedResult, ue.isPassFail as isPassFail, "
		    + "ue.exam.id as examId, ue.exam.name as examName, ue.exam.description as examDescription, ue.exam.examTotalMark as examTotalMark, "
		    + "ue.exam.level.name as level,ue.exam.course.name as course, ue.userAnswers as userAnswers) "
		    + "FROM UserExam ue "
		    + "WHERE ue.user.id = :userId AND ue.exam.id = :examId")
	public UserExamHistoryDto findUserExamHistory(Long userId, Long examId);
}
