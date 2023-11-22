package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexcode.examsystem.model.entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	@Query(value="SELECT COUNT(q) FROM Exam e JOIN e.questions q WHERE e.id = :examId")
	int countQuestionsForExam(Long examId);

	@Query(value="SELECT  COUNT(DISTINCT ue.id) * 100.0 / (COUNT(DISTINCT e.id))" + "FROM Exam e " + "JOIN e.course c "
			+ "LEFT JOIN UserExam ue ON e.id = ue.exam.id " + "WHERE c.id = :courseId")
	long getPercentage(Long courseId);
	
	@Query(value="SELECT COUNT(DISTINCT ue.user.id) FROM UserExam ue WHERE ue.exam.id = :examId")
	int getTotalNoOfStudentsOfEachExam(Long examId);

	@Query(value="SELECT COUNT(e) FROM Exam e WHERE e.level.id=:levelId AND e.course.id=:courseId")
    int getCountByLevelAndCourse(Long levelId,Long courseId);
	
}
