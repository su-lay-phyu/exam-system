package com.nexcode.examsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.UserExam;
import com.nexcode.examsystem.model.projections.UserExamHistoryProjection;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

	@Query("SELECT ue FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.id = :examId")
	UserExam findByUserExam(Long userId, Long examId);

	@Query(value = "SELECT ue.id as id, ue.exam.name as examName, ue.exam.description as examDescription, ue.exam.noOfQuestion as noOfQuestion, ue.exam.examTotalMark as examTotalMark, ue.exam.course.name as courseName, ue.exam.level.name as levelName, ue.obtainedResult as obtainedResult, ue.isPassFail as isPassFail FROM UserExam ue JOIN ue.exam e WHERE ue.user.id = :userId")
	List<UserExamHistoryProjection> findAllExamHistoryByUserId(@Param("userId") Long userId);

//	----( i need to check the total no of exams and if the user take all of the exam that have in course we said that that student is completed ?)
//	@Query("SELECT COUNT(ue) FROM UserExam ue WHERE ue.exam.course.id = :courseId AND ue.isPassFail = true")
//	Integer getCompletedStudentCount(@Param("courseId") Long courseId);
	
	@Query("SELECT COUNT(DISTINCT ue.exam.id) FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.course.id = :courseId")
	Integer getDistinctTakenExamCount(@Param("userId") Long userId, @Param("courseId") Long courseId);

	
	@Query("SELECT ue FROM UserExam ue WHERE ue.exam.id = :examId")
	List<UserExam>findAllUserByExamId(@Param("examId")Long examId);
	
	@Query("SELECT COUNT(ue) FROM UserExam ue WHERE ue.exam.id = :examId AND ue.isPassFail = true")
    Integer getPassCountByExamId(@Param("examId") Long examId);

    @Query("SELECT COUNT(ue) FROM UserExam ue WHERE ue.exam.id = :examId AND ue.isPassFail = false")
    Integer getFailCountByExamId(@Param("examId") Long examId);
    
    @Query("SELECT e FROM UserExam ue JOIN ue.exam e")
    List<Exam>getAllTakenExams();
}
 