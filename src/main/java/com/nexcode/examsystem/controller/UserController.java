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
import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.mapper.UserExamMapper;
import com.nexcode.examsystem.mapper.UserMapper;
import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.dtos.UserExamHistoryProjection;
import com.nexcode.examsystem.model.exception.NotFoundException;
import com.nexcode.examsystem.model.requests.ChangePasswordRequest;
import com.nexcode.examsystem.model.requests.EmailRequest;
import com.nexcode.examsystem.model.requests.NewPasswordRequest;
import com.nexcode.examsystem.model.requests.UserAnswerRequest;
import com.nexcode.examsystem.model.requests.UserRequest;
import com.nexcode.examsystem.model.requests.VerifyOtpRequest;
import com.nexcode.examsystem.model.responses.ApiResponse;
import com.nexcode.examsystem.model.responses.ExamResponse;
import com.nexcode.examsystem.model.responses.QuestionResponse;
import com.nexcode.examsystem.model.responses.StudentCourseResponse;
import com.nexcode.examsystem.model.responses.UserExamResponse;
import com.nexcode.examsystem.security.CurrentUser;
import com.nexcode.examsystem.security.UserPrincipal;
import com.nexcode.examsystem.service.CourseService;
import com.nexcode.examsystem.service.ExamService;
import com.nexcode.examsystem.service.UserExamService;
import com.nexcode.examsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final CourseService courseService;
	private final UserService userService;
	private final ExamService examService;
	private final UserExamService userExamService;

	private final UserMapper userMapper;
	private final CourseMapper courseMapper;
	private final QuestionMapper questionMapper;
	private final UserExamMapper userExamMapper;
	private final ExamMapper examMapper;

	// password process
	@PostMapping("/forgot-password")
	public ResponseEntity<?> processForgetPassword(@RequestBody EmailRequest request) {
		UserDto foundedUser = userService.findUserByEmailAddress(request.getEmail());
		if (foundedUser == null) {
			throw new NotFoundException("Email not found");
		}
		userService.generateOneTimePassword(foundedUser);
		return new ResponseEntity<>("Successfully password reset.",HttpStatus.OK);
	}

	@PutMapping("/change-password")
	public ResponseEntity<?> processForgetPassword(@CurrentUser UserPrincipal currentUser,
			@RequestBody ChangePasswordRequest request) {
		String requestOldPassword = request.getOldPassword();
		String requestNewPassword = request.getNewPassword();
		String email = currentUser.getEmail();
		userService.changePassword(email, requestOldPassword, requestNewPassword);
		return new ResponseEntity<>("Successfully changed password.",HttpStatus.OK);
	}

	@PostMapping("/verified-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
		String email = request.getEmail();
		String storedOtp = request.getOtp();
		userService.validateOtp(email, storedOtp);
		return new ResponseEntity<>("validate otp",HttpStatus.OK);
	}

	@PostMapping("/set-new-password")
	public ResponseEntity<?> setNewForgotPassword(@RequestBody NewPasswordRequest request) {
		String email = request.getEmail();
		String password = request.getNewpassword();
		userService.setNewResetPassword(email, password);
		return new ResponseEntity<>("Successfully updated New Password",HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<UserDto> dtos = userService.getAllUser();
		return new ResponseEntity<>(userMapper.toResponseList(dtos), HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<?> getStudentByEmail(@RequestParam("input") String input) {
		UserDto foundedUser = userService.findUserByEmailOrRollNo(input);
		if (foundedUser != null) {
			return new ResponseEntity<>(userMapper.toResponse(foundedUser), HttpStatus.FOUND);
		}
		return new ResponseEntity<>(foundedUser, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/student-signup")
	public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
		String email = request.getEmail();
		UserDto existingUser = userService.findUserByEmailAddress(email);
		if (existingUser != null) {
	        return new ResponseEntity<>("This email is already in use. Please use another one.",HttpStatus.CONFLICT);
		}
		userService.signUpUser(request);
		return new ResponseEntity<>("Signup successful. An email has been sent for verification.",HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody UserRequest request) {
		UserDto updatedStudent = userService.updateStudent(id, request);
		return new ResponseEntity<>(userMapper.toResponse(updatedStudent),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		userService.deleteStudent(id);
		return new ResponseEntity<>("user deleted successfully",HttpStatus.OK);
	}
	// Student Dashboard
	// sign up course of current user
	@GetMapping("/courses")
	public ResponseEntity<?> getCoursesByUser(@CurrentUser UserPrincipal currentUser) {
		String email = currentUser.getEmail();
		List<CourseDto> dtos = userService.getAllCourseByUserEmail(email);
		List<StudentCourseResponse> responses = courseMapper.toStudentCourseResponseList(dtos);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	//search course by course id at login user
	@GetMapping("/course")
	public ResponseEntity<?> getCourseByCourseId(@CurrentUser UserPrincipal currentUser, @RequestParam("id") Long id) {
		String email = currentUser.getEmail();
		CourseDto foundedCourse = userService.findUserCourseById(email, id);
		return new ResponseEntity<>(courseMapper.toResponse(foundedCourse), HttpStatus.FOUND);
	}
	@GetMapping("/course/{id}/exams")
	public List<ExamResponse> getSignUpExams(@PathVariable Long id) {
		List<ExamDto> dtos = courseService.getAllPublishedExams(id);
		return examMapper.toResponseList(dtos);

	}
	@GetMapping("/exam/{id}/start")
	public List<QuestionResponse> startExam(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		List<QuestionDto> dtos = null;
		String email = currentUser.getEmail();
		ExamDto foundedExam = examService.findExamById(id);
		UserDto foundedUserDto = userService.findUserByEmailAddress(email);
		if (foundedUserDto != null) {
			dtos = examService.getRandomQuestionsForExam(foundedExam);
			// create user exam for that user
			userExamService.createUserExam(currentUser.getId(), foundedExam.getId());
		}
		return questionMapper.toResponseList(dtos);
	}

	@PostMapping("/exam/{id}/submit")
	public ResponseEntity<?> submitExam(@CurrentUser UserPrincipal currentUser, @PathVariable Long id,@RequestBody List<UserAnswerRequest> userAnswers) 
	{
		Long userId = currentUser.getId();
		UserExamDto dto = userExamService.findUserExamByUserAndExam(userId, id, userAnswers);
		UserExamResponse response = userExamMapper.toResponse(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/exam/history")
	public ResponseEntity<?> getExamHistoryForUser(@CurrentUser UserPrincipal currentUser) {
		List<UserExamHistoryProjection> list = userExamService.getAllExamHistoryByUserId(currentUser.getEmail());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	// this one is when the user try to view his/her taken answer sheet
	@GetMapping("/exam/history/{id}")
	public ResponseEntity<?> getExamHistoryDetails(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		UserExamDto dto = userExamService.getEachExamAllHistory(currentUser.getEmail(), id);
		UserExamResponse response = userExamMapper.toResponse(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
