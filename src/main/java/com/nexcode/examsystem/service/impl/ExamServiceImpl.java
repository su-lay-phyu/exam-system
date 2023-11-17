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
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.exception.NotFoundException;
import com.nexcode.examsystem.model.requests.AnswerRequest;
import com.nexcode.examsystem.model.requests.ExamPublishedRequest;
import com.nexcode.examsystem.model.requests.ExamRequest;
import com.nexcode.examsystem.model.requests.QuestionRequest;
import com.nexcode.examsystem.repository.CourseRepository;
import com.nexcode.examsystem.repository.ExamRepository;
import com.nexcode.examsystem.repository.LevelRepository;
import com.nexcode.examsystem.repository.QuestionRepository;
import com.nexcode.examsystem.service.ExamService;

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

	// find exam with id
	public Exam findExam(Long id) {
		return examRepository.findById(id).orElseThrow(() -> new NotFoundException("Exam not found"));
	}

	@Override
	public ExamDto findExamById(Long id) {
		Exam foundedExam = findExam(id);
		return examMapper.toDto(foundedExam);
	}

	@Override
	public List<ExamDto> getAllExam() {
		return examMapper.toDtoList(examRepository.findAll());
	}

	@Override
	public List<QuestionDto> getAllQuestionById(Long id) {
		Exam foundedExam = findExam(id);
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
		Exam foundedExam = findExam(id);
		if(foundedExam.getIsPublished()==true)
		{
			throw new BadRequestException("You can't be edit because the exam is already published");
		}
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
		boolean isPublished = request.isPublished();
		Exam foundedExam = findExam(id);
		if (isPublished) {
			if (!checkForEnoughQuestion(foundedExam))
				return false;
		}
		foundedExam.setIsPublished(isPublished);
		foundedExam.setExamPublishDate(isPublished ? new Date() : null);
		foundedExam.setActive(isPublished);
		examRepository.save(foundedExam);
		return true;
	}

	public boolean checkForEnoughQuestion(Exam exam) {
		int requiredQuestionCount = exam.getNoOfQuestion();
		int actualQuestionCount = examRepository.countQuestionsForExam(exam.getId());
		if (actualQuestionCount < requiredQuestionCount) {
			throw new BadRequestException("Not enough questions to publish the exam.");
		}
		return true;
	}

	@Override
	public List<QuestionDto> getRandomQuestionsForExam(ExamDto dto) {
		int no = dto.getNoOfQuestion();
		List<QuestionDto> allQuestions = dto.getQuestions();
		Collections.shuffle(allQuestions);
		int numberOfQuestionsToTake = Math.min(no, allQuestions.size());
		List<QuestionDto> questionList = allQuestions.subList(0, numberOfQuestionsToTake);
		return questionList;
	}

	@Override
	public boolean deleteExam(Long id) {
		Exam foundedExam=examRepository.findById(id).orElseThrow(()->new NotFoundException("Exam not found"));
		if(foundedExam.getIsPublished()==true)
		{
			throw new BadRequestException("You can't be edit because the exam is already published");
		}
		foundedExam.setActive(false);
		examRepository.save(foundedExam);
		return true;
	}
}
