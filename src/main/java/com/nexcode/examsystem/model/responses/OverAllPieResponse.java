package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OverAllPieResponse {
	private String courseName;
	private Integer totalNoOfStudent;
}
