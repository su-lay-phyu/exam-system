package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.LevelDto;
import com.nexcode.examsystem.model.entities.Level;
import com.nexcode.examsystem.model.responses.LevelResponse;

public interface LevelMapper {
	
	public LevelDto toDto(Level level);
	public List<LevelDto>toDtoList(List<Level>levels);
	public LevelResponse toResponse(LevelDto dto);
}
