package com.nexcode.examsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.model.dtos.UserReportProjection;
import com.nexcode.examsystem.model.responses.CourseExamListReportResponse;
import com.nexcode.examsystem.model.responses.CourseExamReportPieResponse;
import com.nexcode.examsystem.model.responses.ExamStudentReportResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;
import com.nexcode.examsystem.model.responses.StudentPassFailCountResponse;
import com.nexcode.examsystem.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
	private final ReportService reportService;
	//overall report
	@GetMapping
	public List<OverAllReportResponse>getOverAllReport()
	{
		return reportService.getOverAllReports();
	}
	//course report 
	@GetMapping("/course/{id}/pie")
	public List<CourseExamReportPieResponse>getExamLevelPie(@PathVariable Long id)
	{
		return reportService.getExamByLevel(id);
	}
	@GetMapping("/course/{id}")
	public List<CourseExamListReportResponse>getExamListByCourseId(@PathVariable Long id)
	{
		return reportService.getAllCourseExamListReport(id);
	}
	//exam report
	@GetMapping("/exam/{id}/pie")
	public StudentPassFailCountResponse getDetailPieReportEachExam(@PathVariable Long id)
	{
		return reportService.getCountPassFail(id);
	}
	@GetMapping("/exam/{id}")
	public List<ExamStudentReportResponse>getDetailReportEachExam(@PathVariable Long id)
	{
		return reportService.getExamStudent(id);
	}
	//student report
	@GetMapping("/student/{id}")
	public List<UserReportProjection>getStudentReport(@PathVariable Long id)
	{
		return reportService.getStudentReport(id);
	}
}
