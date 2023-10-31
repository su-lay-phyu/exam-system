package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.RoleMapper;
import com.nexcode.examsystem.model.dtos.RoleDto;
import com.nexcode.examsystem.model.entities.Role;
import com.nexcode.examsystem.model.requests.RoleRequest;
import com.nexcode.examsystem.model.responses.RoleResponse;

@Component
public class RoleMapperImpl implements RoleMapper{

	@Override
	public RoleDto toDto(Role role) {
		if(role==null) {
			return null;
		}
		RoleDto dto=new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}

	@Override
	public List<RoleDto> toDtoList(List<Role> roles) {
		return roles.stream().map(r->toDto(r)).collect(Collectors.toList());
	}

	@Override
	public RoleDto toDto(RoleRequest request) {
		if(request==null)
		{
			return null;
		}
		RoleDto dto=new RoleDto();
		dto.setName(request.getName());
		return dto;
	}

	@Override
	public RoleResponse toResponse(RoleDto dto) {
		if(dto==null)
		{
			return null;
		}
		RoleResponse response=new RoleResponse();
		response.setId(dto.getId());
		response.setName(dto.getName());
		return response;
	}

}
