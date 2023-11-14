package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.responses.CourseExamListReportResponse;
import com.nexcode.examsystem.model.responses.CourseExamReportPieResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;

public interface ReportService {
	
	public List<OverAllReportResponse> getOverAllReports();
	public List<CourseExamListReportResponse> getAllCourseExamListReport(Long id);
	public List<CourseExamReportPieResponse> getExamByLevel(Long id);
}
