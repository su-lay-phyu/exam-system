package com.nexcode.examsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.User;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findById(Long id);

	@Query(value = "SELECT c FROM Course c WHERE c.isActive = true")
	List<Course> findAll();

	@Query(value = "SELECT u FROM User u JOIN u.courses c WHERE c.id = :courseId")
	List<User> getAllUsersByCourseId(Long courseId);

	Optional<Course> findByName(String name);

	@Query(value = "SELECT COUNT(*) FROM Exam e WHERE e.course.id = :courseId")
	int getTotalExamsInCourse(Long courseId);

	@Query(value = "SELECT e FROM Exam e WHERE e.course.id = :courseId AND e.isPublished = true")
	List<Exam> getAllPublishedExams(Long courseId);

//	@Query(value="SELECT e FROM Exam e " +
//	           "WHERE e.course.id = :courseId " +
//	           "  AND e.isPublished = true " +
//	           "  AND e.id NOT IN (SELECT ue.exam.id FROM UserExam ue WHERE ue.user.id = :userId)")
//	List<Exam> getAllUntakenPublishedExams(@Param("courseId") Long courseId, @Param("userId") Long userId);
	
//	@Query("SELECT e FROM Exam e " +
//		       "WHERE e.course.id = :courseId " +
//		       "  AND e.isPublished = true " +
//		       "  AND e.id NOT IN (SELECT ue.exam.id FROM UserExam ue WHERE ue.user.id = :userId)")
//	List<Exam> getAllUntakenPublishedExams(@Param("courseId") Long courseId, @Param("userId") Long userId);
	
	@Query("SELECT e FROM Exam e " +
		       "WHERE e.course.id = :courseId " +
		       "  AND e.isPublished = true " +
		       "  AND e.id NOT IN (SELECT ue.exam.id FROM UserExam ue WHERE ue.user.id = :userId) " +
		       "  AND :courseId IN (SELECT uc.id FROM User u JOIN u.courses uc WHERE u.id = :userId)")
		List<Exam> getAllUntakenPublishedExams(@Param("courseId") Long courseId, @Param("userId") Long userId);


	@Query(value = "SELECT c FROM Course c JOIN c.users u WHERE u.id = :userId AND c.id = :courseId")
	Course findUserCourseById(Long userId, Long courseId);

	@Query(value = "SELECT COUNT(*) FROM Course c JOIN c.users u WHERE c.id = :courseId")
	int getTotalNoOfStudent(Long courseId);

}
