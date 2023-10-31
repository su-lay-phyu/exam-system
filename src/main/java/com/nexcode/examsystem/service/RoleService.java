package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.RoleDto;
import com.nexcode.examsystem.model.entities.Role;

public interface RoleService {
	public RoleDto addRole(RoleDto roleDto);
	public RoleDto updateRole(Long id, Role role) ;
	public List<RoleDto> getAllRoles();

}
