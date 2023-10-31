package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.responses.UserResponse;

public interface UserMapper {
	public UserDto toDto(User user) ;
	public List<UserDto> toDtoList(List<User> users);
	public UserResponse toResponse(UserDto userDto) ;
	public List<UserResponse> toResponseList(List<UserDto> userDtos) ;
}
