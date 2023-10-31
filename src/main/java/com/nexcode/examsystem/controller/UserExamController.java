package com.nexcode.examsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.UserMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/api/user/exam")
public class UserExamController {
	
	private final ExamMapper examMapper;
	private final UserMapper userMapper;
	
}
