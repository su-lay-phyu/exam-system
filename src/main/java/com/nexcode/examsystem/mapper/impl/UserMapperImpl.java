package com.nexcode.examsystem.mapper.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.CourseMapper;
import com.nexcode.examsystem.mapper.RoleMapper;
import com.nexcode.examsystem.mapper.UserMapper;
import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.responses.UserResponse;

@Component
public class UserMapperImpl implements UserMapper{

	private final RoleMapper roleMapper;
	private final CourseMapper courseMapper;
	
	public UserMapperImpl(RoleMapper roleMapper, CourseMapper courseMapper) {
		this.roleMapper = roleMapper;
		this.courseMapper = courseMapper;
	}

	@Override
	public UserDto toDto(User user) {
		
		if(user==null)
		{
			return null;
		}
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setRollNo(user.getRollNo());
		userDto.setUsername(user.getUsername());;
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhone(user.getPhone());
		userDto.setRoles(roleMapper.toDtoList(user.getRoles()));
		userDto.setCategories(courseMapper.toDtoList(user.getCourses()));
		return userDto;
	}

	@Override
	public List<UserDto> toDtoList(List<User> users) {
		if(users==null)
		{
			return null;
		}
		return users.stream().map(user->toDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserResponse toResponse(UserDto userDto) {
		if(userDto==null)
		{
			return null;
		}
		UserResponse userResponse=new UserResponse();
		userResponse.setId(userDto.getId());
		userResponse.setRollNo(userDto.getRollNo());
		userResponse.setUsername(userDto.getUsername());
		userResponse.setEmail(userDto.getEmail());
		userResponse.setPhone(userDto.getPhone());
		userResponse.setCourses(courseMapper.toResponseList(userDto.getCategories()));
		return userResponse;
	}

	@Override
	public List<UserResponse> toResponseList(List<UserDto> userDtos) {
		return userDtos.stream().map(u->toResponse(u)).collect(Collectors.toList());
	}

}
