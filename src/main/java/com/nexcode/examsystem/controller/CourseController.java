package com.nexcode.examsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.mapper.CourseMapper;
import com.nexcode.examsystem.mapper.UserMapper;
import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.exception.NotFoundException;
import com.nexcode.examsystem.model.requests.CourseRequest;
import com.nexcode.examsystem.model.responses.ApiResponse;
import com.nexcode.examsystem.model.responses.CourseResponse;
import com.nexcode.examsystem.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
	
	private final CourseService courseService;
	
	private final CourseMapper courseMapper;
	private final UserMapper userMapper;
	
	@GetMapping
	public List<CourseResponse> getAllCourses()
	{
		List<CourseDto>dtos=courseService.getAllCourses();
		return courseMapper.toResponseList(dtos);
	}
	// filter users by course Id
	@GetMapping("/users/filter")
	public ResponseEntity<?> getUsersByCourseId(@RequestParam("id") Long courseId) {
	    List<UserDto> dtos = courseService.getAllUserByCourseId(courseId);
	    return new ResponseEntity<>(userMapper.toResponseList(dtos), HttpStatus.OK);
	}
	//search by course name
	@GetMapping("/search")
	public ResponseEntity<?> getCourseByName(@RequestParam("courseName") String courseName) {
	    CourseDto foundedCourse = courseService.findByName(courseName);
	    if(foundedCourse==null)
	    {
	    	throw new NotFoundException("Course Not Found");
	    }
	    return new ResponseEntity<>(courseMapper.toResponse(foundedCourse), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<?>createNewCourse(@RequestBody CourseRequest request)
	{
		CourseDto existingCourse=courseService.findByName(request.getName());
		if (existingCourse != null) {
		    return new ResponseEntity<>(new ApiResponse(false, "Course already exists"), HttpStatus.CONFLICT);
		} else {
		    CourseDto dto = courseMapper.toDto(request);
		    boolean isAdded=courseService.addCourse(dto);
		    if(isAdded)
		    {
		    	return new ResponseEntity<>(new ApiResponse(isAdded, "Course is added successfully"), HttpStatus.CREATED);
		    }
		}
		throw new AppException("An error occurred while processing the adding course");

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateCourse(@PathVariable Long id,@RequestBody CourseRequest request)
	{
		CourseDto existingCourse=courseService.findByName(request.getName());
		if (existingCourse != null) {
		    return new ResponseEntity<>(new ApiResponse(false, "Course already exists"), HttpStatus.CONFLICT);
		} 
		else
		{
			CourseDto dto=courseMapper.toDto(request);
			CourseDto updatedDto=courseService.updateCourse(id, dto);
			if(updatedDto!=null)
			{
				return new ResponseEntity<>(new ApiResponse(true, "Course is successfully updated"), HttpStatus.OK);
			}
		}
		throw new AppException("An error occurred while processing the update course");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteCourse(@PathVariable Long id)
	{
		boolean isDeleted=courseService.deleteCourse(id);
		if(isDeleted)
		{
			return new ResponseEntity<>(isDeleted,HttpStatus.OK);
		}
		throw new AppException("An error occurred while processing the delete course");
	}
}
