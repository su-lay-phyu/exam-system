package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.responses.OverAllPieResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;

public interface ReportService {
	
	public List<OverAllPieResponse>getTotalStudentByCourse();

	public List<OverAllReportResponse> getOverAllReports();
}
