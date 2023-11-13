package com.nexcode.examsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.responses.OverAllPieResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;
import com.nexcode.examsystem.repository.CourseRepository;
import com.nexcode.examsystem.service.ReportService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final CourseRepository courseRepository;

	
	public List<Course>getAllCourses()
	{
		return courseRepository.findAll();
	}
	@Override
	public List<OverAllPieResponse> getTotalStudentByCourse() {
		List<Course>courses=getAllCourses();
		List<OverAllPieResponse>list=new ArrayList<>();
		for(Course c:courses)
		{
			Integer noOfStudent=courseRepository.getTotalNoOfStudent(c.getId());
			OverAllPieResponse response=new OverAllPieResponse();
			response.setCourseName(c.getName());
			response.setTotalNoOfStudent(noOfStudent);
			list.add(response);
		}
		return list;
	}

	@Override
	public List<OverAllReportResponse> getOverAllReports() {
		List<Course>courses=getAllCourses();
		List<OverAllReportResponse>list=new ArrayList<>();
		for(Course c:courses)
		{
			Integer noOfStudent=courseRepository.getTotalNoOfStudent(c.getId());
			OverAllReportResponse response=new OverAllReportResponse();
			response.setCourseName(c.getName());
			response.setTotalNoOfStudents(noOfStudent);
		}
		return null;
	}

}
