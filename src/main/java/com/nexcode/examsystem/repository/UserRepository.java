package com.nexcode.examsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.projections.UserReportProjection;


public interface UserRepository extends JpaRepository<User, Long>{

	Optional <User> findByEmail(String email);
	
	@Query("SELECT MAX(CAST(SUBSTRING(u.rollNo, 4, LENGTH(u.rollNo)) AS int)) FROM User u WHERE u.rollNo LIKE 'stu%'")
	Integer findMaxRollNoForStudents();
	
	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.courses c JOIN u.roles r WHERE r.name = 'user'")
	List<User> findAllUserWithCategories();
	
    @Query("SELECT u.courses FROM User u WHERE u.id=:userId")
	List<Course>findAllCourseWithUserEmail(@Param("userId")Long userId);
    
    @Query("SELECT ue.exam.name as examName, ue.exam.course.name as courseName, ue.exam.level.name as levelName, ue.obtainedResult as obtainedResult, ue.isPassFail as isPassFail FROM UserExam ue LEFT JOIN ue.exam LEFT JOIN ue.exam.course LEFT JOIN ue.exam.level WHERE ue.user.id = :userId")
    List<UserReportProjection> findUserTakenExams(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.email = :input or u.rollNo=:input")
	Optional<User> findbyInput(String input);

}
