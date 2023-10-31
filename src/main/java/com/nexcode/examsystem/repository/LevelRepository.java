package com.nexcode.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexcode.examsystem.model.entities.Level;

public interface LevelRepository extends JpaRepository<Level, Long>{

	boolean existsByName(String levelName);

}
