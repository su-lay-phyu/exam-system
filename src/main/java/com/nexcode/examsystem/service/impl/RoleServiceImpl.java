package com.nexcode.examsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcode.examsystem.mapper.RoleMapper;
import com.nexcode.examsystem.model.dtos.RoleDto;
import com.nexcode.examsystem.model.entities.Role;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.repository.RoleRepository;
import com.nexcode.examsystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public RoleDto addRole(RoleDto roleDto) {
		Role role=new Role();
		role.setName(roleDto.getName());
		Role savedRole=roleRepository.save(role);
		System.out.println("savedRole id "+savedRole.getId());
		RoleDto dto=roleMapper.toDto(savedRole);
		return dto;
	}

	@Override
	public RoleDto updateRole(Long id, Role role) {
		Role foundedRole=roleRepository.findById(id).orElseThrow(()->new BadRequestException("Role not found"));
		foundedRole.setName(role.getName());
		return roleMapper.toDto(foundedRole);
	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<Role>roles=roleRepository.findAll();
		List<RoleDto>roleDtos=roles.stream().map(r->roleMapper.toDto(r)).collect(Collectors.toList());
		return roleDtos;
	}

}
