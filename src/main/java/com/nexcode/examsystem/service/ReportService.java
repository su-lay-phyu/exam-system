package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.projections.UserReportProjection;
import com.nexcode.examsystem.model.responses.CourseExamListReportResponse;
import com.nexcode.examsystem.model.responses.CourseExamReportPieResponse;
import com.nexcode.examsystem.model.responses.ExamStudentReportResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;
import com.nexcode.examsystem.model.responses.StudentPassFailCountResponse;

public interface ReportService {
	
	public List<OverAllReportResponse> getOverAllReports();
	public List<CourseExamListReportResponse> getAllCourseExamListReport(Long id);
	public List<CourseExamReportPieResponse> getExamByLevel(Long id);
	public List<ExamStudentReportResponse> getExamStudent(Long examId);
	public List<UserReportProjection> getStudentReport(Long id);
	public StudentPassFailCountResponse getCountPassFail(Long id);
}
