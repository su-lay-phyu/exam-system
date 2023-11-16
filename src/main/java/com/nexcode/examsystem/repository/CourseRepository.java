package com.nexcode.examsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.User;


public interface CourseRepository extends JpaRepository<Course,Long>{

	public Optional<Course>findById(Long id);
	
	@Query("SELECT c FROM Course c WHERE c.isActive = true")
	public List<Course>findAll();
	
	@Query("SELECT u FROM User u JOIN u.courses c WHERE c.id = :courseId")
	public List<User> getAllUsersByCourseId(@Param("courseId") Long courseId);

	public Optional<Course> findByName(String name); 

	@Query("SELECT e FROM Exam e WHERE e.course.id = :courseId")
	List<Exam> getAllExamsByCourseId(@Param("courseId") Long courseId);
	
	@Query("SELECT COUNT(*) FROM Exam e WHERE e.course.id = :courseId")
	Integer getTotalExamsInCourse(@Param("courseId") Long courseId);
	
	@Query("SELECT e FROM Exam e WHERE e.course.id = :courseId AND e.isPublished = true")
	List<Exam> getAllPublishedExams(@Param("courseId") Long courseId);
	
	@Query("SELECT c FROM Course c JOIN c.users u WHERE u.id = :userId AND c.id = :courseId")
	public Course findUserCourseById(@Param("userId") Long userId, @Param("courseId") Long courseId);
	
	@Query("SELECT COUNT(*) FROM Course c JOIN c.users u WHERE c.id = :courseId")
	public Integer getTotalNoOfStudent(@Param("courseId") Long courseId);
	
}
