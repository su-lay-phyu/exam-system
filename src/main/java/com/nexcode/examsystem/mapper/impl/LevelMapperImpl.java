package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.LevelMapper;
import com.nexcode.examsystem.model.dtos.LevelDto;
import com.nexcode.examsystem.model.entities.Level;
import com.nexcode.examsystem.model.responses.LevelResponse;
@Component
public class LevelMapperImpl implements LevelMapper{
	
	

	@Override
	public LevelDto toDto(Level level) {
		if(level==null)
		{
			return null;
		}
		LevelDto dto=new LevelDto();
		dto.setId(level.getId());
		dto.setName(level.getName());
		return dto;
	}

	@Override
	public List<LevelDto> toDtoList(List<Level> levels) {
		if(levels==null)
		{
			return null;
		}
		return levels.stream().map(l->toDto(l)).collect(Collectors.toList());
	}

	@Override
	public LevelResponse toResponse(LevelDto dto) {
		LevelResponse response=new LevelResponse();
		response.setId(dto.getId());
		response.setLevel(dto.getName());
		return response;
	}
}
