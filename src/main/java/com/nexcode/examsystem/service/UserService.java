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
	public boolean signUpUser(UserRequest request) ;
	public boolean changePassword(String email, String requestOldPassword, String requestNewPassword);
	public boolean validateOtp(String email, String otp);
	public boolean setNewResetPassword(String email, String password) ;
	public boolean generateOneTimePassword(UserDto userDto);
	public boolean updateStudent(Long id,UserRequest request);
	public CourseDto findUserCourseById(String email,Long id);
	public boolean deleteStudent(Long id);
	
}
