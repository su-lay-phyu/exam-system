package com.nexcode.examsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.Level;
import com.nexcode.examsystem.model.entities.UserExam;
import com.nexcode.examsystem.model.responses.CourseExamListReportResponse;
import com.nexcode.examsystem.model.responses.CourseExamReportPieResponse;
import com.nexcode.examsystem.model.responses.ExamStudentReportResponse;
import com.nexcode.examsystem.model.responses.OverAllReportResponse;
import com.nexcode.examsystem.repository.CourseRepository;
import com.nexcode.examsystem.repository.ExamRepository;
import com.nexcode.examsystem.repository.LevelRepository;
import com.nexcode.examsystem.repository.UserExamRepository;
import com.nexcode.examsystem.service.ReportService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final CourseRepository courseRepository;
	private final UserExamRepository userExamRepository;
	private final ExamRepository examRepository;
	private final LevelRepository levelRepository;
	
	public List<Course>getAllCourses()
	{
		return courseRepository.findAll();
	}
	@Override
	public List<OverAllReportResponse> getOverAllReports() {
		List<Course>courses=getAllCourses();
		List<OverAllReportResponse>list=new ArrayList<>();
		for (Course c : courses) {
	        Integer totalNoOfStudent = courseRepository.getTotalNoOfStudent(c.getId());
	        Integer completeStudents = userExamRepository.getCompletedStudentCount(c.getId());

	        OverAllReportResponse response = new OverAllReportResponse();
	        response.setCourseName(c.getName());
	        response.setTotalNoOfStudents(totalNoOfStudent);
	        response.setCompleteStudents(completeStudents);
	        Integer NoOfInProgressStudents=totalNoOfStudent-completeStudents;
	        response.setInProgressStudents(NoOfInProgressStudents);
	        list.add(response);
	    }
		return list;
	}
	@Override
	public List<CourseExamListReportResponse> getAllCourseExamListReport(Long id) {
		List<Exam>examList=courseRepository.getAllPublishedExams(id);
		List<CourseExamListReportResponse>list=new ArrayList<>();
		for(Exam e : examList)
		{
			CourseExamListReportResponse response=new CourseExamListReportResponse();
			response.setExamId(e.getId());
			response.setExamName(e.getName());
			response.setExamPublishedDate(e.getExamPublishDate().toString());//check
			response.setLevelName(e.getLevel().getName());//
			Integer noOfCompletedStudents=examRepository.getNoOfCompletedCount(e.getId());
			Integer totalNoOfStudents=courseRepository.getTotalNoOfStudent(id);
			System.out.println("No of completed students"+noOfCompletedStudents);
			System.out.println("total no of student "+totalNoOfStudents);
			response.setCompletedStudents(noOfCompletedStudents);
			response.setInProgressStudents(totalNoOfStudents-noOfCompletedStudents);
			list.add(response);
		}
		return list;
	}
	@Override
	public List<CourseExamReportPieResponse> getExamByLevel(Long id) {
		 List<CourseExamReportPieResponse> results = new ArrayList<>();

		    List<Level> levels = levelRepository.findAll();
		    
		    for (Level level : levels) {
		        CourseExamReportPieResponse response = new CourseExamReportPieResponse();
		        response.setLevelName(level.getName());
		        
		        int examCount = examRepository.getCountByLevelAndCourse(level.getId(), id);
		        response.setNoOfExams(examCount);

		        results.add(response);
		    }

		    return results;
	}
	@Override
	public List<ExamStudentReportResponse> getExamStudent(Long examId) {
		List<UserExam>userExams=userExamRepository.findAllUserByExamId(examId);
		List<ExamStudentReportResponse>list=new ArrayList<>();
		for(UserExam ue:userExams)
		{
			ExamStudentReportResponse response=new ExamStudentReportResponse();
			response.setId(ue.getUser().getId());
			response.setRollNo(ue.getUser().getRollNo());
			response.setUserName(ue.getUser().getUsername());
			response.setEmail(ue.getUser().getEmail());
			response.setObtainedMark(ue.getObtainedResult());
			response.setPassFail(ue.getIsPassFail());
			list.add(response);
		}
		return list;
	}
	

}
