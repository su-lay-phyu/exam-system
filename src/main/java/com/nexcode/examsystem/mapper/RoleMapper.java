package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.RoleDto;
import com.nexcode.examsystem.model.entities.Role;
import com.nexcode.examsystem.model.requests.RoleRequest;
import com.nexcode.examsystem.model.responses.RoleResponse;

public interface RoleMapper {

	public RoleDto toDto(Role role);
	public List<RoleDto> toDtoList(List<Role> roles) ;
	public RoleDto toDto(RoleRequest request) ;
	public RoleResponse toResponse(RoleDto dto);
}
