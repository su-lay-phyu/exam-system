package com.nexcode.examsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexcode.examsystem.model.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
	boolean existsByName(String roleName);
}
