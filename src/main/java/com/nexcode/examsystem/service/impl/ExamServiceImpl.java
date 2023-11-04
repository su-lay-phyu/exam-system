package com.nexcode.examsystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexcode.examsystem.mapper.ExamMapper;
import com.nexcode.examsystem.mapper.QuestionMapper;
import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.QuestionDto;
import com.nexcode.examsystem.model.entities.Answer;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.Question;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.requests.AnswerRequest;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.requests.QuestionRequest;
import com.nexcode.examsystem.repository.CourseRepository;
import com.nexcode.examsystem.repository.ExamRepository;
import com.nexcode.examsystem.repository.LevelRepository;
import com.nexcode.examsystem.repository.QuestionRepository;
import com.nexcode.examsystem.repository.UserRepository;
import com.nexcode.examsystem.service.ExamService;
import com.nexcode.examsystem.service.UserExamSerrvice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

	private final ExamMapper examMapper;
	 private final QuestionMapper questionMapper;

	private final ExamRepository examRepository;
	private final LevelRepository levelRepository;
	private final CourseRepository categoryRepository;
	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;

	@Override
	public ExamDto findExamById(Long id) {

		return examMapper.toDto(examRepository.findById(id).orElse(null));
	}
	@Override
	public List<ExamDto> getAllExamDetails() {
		return examMapper.toDtoList(examRepository.findAll());
	}
	@Override
	public List<QuestionDto> getAllQuestionById(Long id) {
		Exam foundedExam=examRepository.findById(id).orElseThrow(()->new BadRequestException("Exam not found"));
		return questionMapper.toDtoList(foundedExam.getQuestions());
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean createExamWithQuestions(ExamRequest request) {
		List<Question> questions = new ArrayList<>();
		for (QuestionRequest questionRequest : request.getQuestions()) {
			Question question = addQuestionToExam(questionRequest);
			questions.add(question);
		}
		Exam savedExam = createExam(request, questions);
		if (savedExam != null) {
			return true;
		} else {
			throw new AppException("Failed to create the exam.");
		}
	}

	public Exam createExam(ExamRequest request, List<Question> questions) {
		Exam exam = new Exam();
		exam.setName(request.getName());
		exam.setDescription(request.getDescription());
		exam.setExamdurationMinutes(request.getExamDurationMinute());
		exam.setExamTotalMark(request.getExamTotalMark());
		exam.setCourse(categoryRepository.findById(request.getCourseId()).orElse(null));
		exam.setLevel(levelRepository.findById(request.getLevelId()).orElse(null));
		exam.setNoOfQuestion(request.getNoOfQuestion());
		exam.setQuestions(questions);

		Exam savedExam = examRepository.save(exam);
		return savedExam;
	}

	public Question addQuestionToExam(QuestionRequest questionRequest) {
		Question question = new Question();
		question.setQuestion(questionRequest.getQuestion());
		question.setActive(true);

		List<AnswerRequest> answerRequests = questionRequest.getAnswers();
		List<Answer> answers = new ArrayList<>();

		for (AnswerRequest request : answerRequests) {
			Answer answer = new Answer();
			answer.setAnswer(request.getAnswer());
			answer.setCorrectAnswer(request.isCorrectAnswer());
			answers.add(answer);
		}
		question.setAnswers(answers);
		questionRepository.save(question);
		return question;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateExam(Long id, ExamRequest request) {
		Exam foundedExam = examRepository.findById(id).orElseThrow(() -> new BadRequestException("Exam not Found"));
		foundedExam.setName(request.getName());
		foundedExam.setDescription(request.getDescription());
		foundedExam.setExamdurationMinutes(request.getExamDurationMinute());
		foundedExam.setExamTotalMark(request.getExamTotalMark());
		foundedExam.setNoOfQuestion(request.getNoOfQuestion());
		foundedExam.setCourse(categoryRepository.findById(request.getCourseId()).orElse(null));
		foundedExam.setLevel(levelRepository.findById(request.getLevelId()).orElse(null));
		if (request.getQuestions() != null && !request.getQuestions().isEmpty()) {
			for (QuestionRequest questionRequest : request.getQuestions()) {
				try {
					Question question = addQuestionToExam(questionRequest);
					foundedExam.getQuestions().add(question);
				} catch (Exception e) {
					throw new AppException("Failed to add a question to the exam: " + e.getMessage());
				}
			}
		}
		examRepository.save(foundedExam);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean setExamPublished(Long id, ExamPublishedRequest request) {
		boolean ispublished = request.isPublished();
		Exam foundedExam = examRepository.findById(id).orElse(null);
		if(ispublished &&checkForEnoughQuestion(foundedExam))
		{
			foundedExam.setIsPublished(ispublished);
			Date currentDate = new Date();
			foundedExam.setExamPublishDate(currentDate);
			foundedExam.setActive(ispublished);
			examRepository.save(foundedExam);
			return true;
		}
		return true;

	}
	public boolean checkForEnoughQuestion(Exam exam)
	{
		int requiredQuestionCount = exam.getNoOfQuestion();
		int actualQuestionCount = examRepository.countQuestionsForExam(exam.getId());
		if (actualQuestionCount < requiredQuestionCount) 
		{
			throw new BadRequestException("Not enough questions to publish the exam.");
		}
		return true;
	}
	@Override
	public List<QuestionDto> getRandomQuestionsForExam(String email,ExamDto dto) 
	{
		User currentUser=userRepository.findByEmail(email).orElseThrow(()->new BadRequestException("email not found"));
		int no = dto.getNoOfQuestion();
		List<QuestionDto> allQuestions = dto.getQuestions();
		Collections.shuffle(allQuestions);
		int numberOfQuestionsToTake = Math.min(no, allQuestions.size());
		List<QuestionDto> questionList = allQuestions.subList(0, numberOfQuestionsToTake);
		return questionList;
	}
}
