package com.nexcode.examsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexcode.examsystem.model.dtos.UserExamHistoryProjection;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.UserExam;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

	@Query(value="SELECT ue FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.id = :examId")
	UserExam findByUserExam(Long userId, Long examId);

	@Query(value = "SELECT ue.id as id, ue.exam.name as examName, ue.exam.description as examDescription, ue.exam.numberOfQuestionsToGenerate as numberOfQuestionsToGenerate, ue.exam.examTotalMark as examTotalMark, ue.exam.course.name as courseName, ue.exam.level.name as levelName, ue.obtainedResult as obtainedResult, ue.isPass as isPass FROM UserExam ue JOIN ue.exam e WHERE ue.user.id = :userId")
	List<UserExamHistoryProjection> findAllExamHistoryByUserId(Long userId);
	
	@Query(value="SELECT COUNT(DISTINCT ue.exam.id) FROM UserExam ue WHERE ue.user.id = :userId AND ue.exam.course.id = :courseId")
	int getDistinctTakenExamCount( Long userId,Long courseId);

	
	@Query(value="SELECT ue FROM UserExam ue WHERE ue.exam.id = :examId")
	List<UserExam>findAllUserByExamId(Long examId);
	
	@Query(value="SELECT COUNT(ue) FROM UserExam ue WHERE ue.exam.id = :examId AND ue.isPass = true")
    int getPassCountByExamId(Long examId);

    @Query(value="SELECT COUNT(ue) FROM UserExam ue WHERE ue.exam.id = :examId AND ue.isPass = false")
    int getFailCountByExamId(Long examId);
    
    @Query(value="SELECT e FROM UserExam ue JOIN ue.exam e")
    List<Exam>getAllTakenExams();
    
    @Query(value="SELECT DISTINCT e FROM UserExam ue JOIN ue.exam e")
    Exam getExam();
}
 