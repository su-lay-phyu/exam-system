package com.nexcode.examsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.model.responses.CourseExamListReportResponse;
import com.nexcode.examsystem.model.responses.CourseExamReportPieResponse;
import com.nexcode.examsystem.model.responses.ExamStudentReportResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;
import com.nexcode.examsystem.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class RepostController {
	
	
	private final ReportService reportService;
	@GetMapping
	public List<OverAllReportResponse>getOverAllReport()
	{
		return reportService.getOverAllReports();
	}
	@GetMapping("/course/{id}")
	public List<CourseExamListReportResponse>getExamListByCourseId(@PathVariable Long id)
	{
		return reportService.getAllCourseExamListReport(id);
	}
	@GetMapping("/course/{id}/pie")
	public List<CourseExamReportPieResponse>getExamLevelPie(@PathVariable Long id)
	{
		return reportService.getExamByLevel(id);
	}
	@GetMapping("/exam/{examId}")
	public List<ExamStudentReportResponse>getDetailReportEachExam(@PathVariable Long examId)
	{
		return reportService.getExamStudent(examId);
	}
}
