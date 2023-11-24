package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.requests.UserRequest;

public interface UserService {
	public List<UserDto>getAllUser();
	public List<CourseDto>getAllCourseByUserEmail(String email);
	public UserDto findUserByEmailAddress(String email);
	public UserDto findUserByEmailOrRollNo(String input);
	public UserDto findUserById(Long id);
	public void signUpUser(UserRequest request) ;
	public void changePassword(String email, String requestOldPassword, String requestNewPassword);
	public void validateOtp(String email, String otp);
	public void setNewResetPassword(String email, String password) ;
	public void generateOneTimePassword(UserDto userDto);
	public UserDto updateStudent(Long id,UserRequest request);
	public CourseDto findUserCourseById(String email,Long id);
	public void deleteStudent(Long id);
	
}
