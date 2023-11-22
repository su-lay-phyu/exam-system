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
import com.nexcode.examsystem.repository.UserExamRepository;
import com.nexcode.examsystem.service.ExamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {

	private final ExamMapper examMapper;
	private final QuestionMapper questionMapper;
	private final ExamRepository examRepository;
	private final LevelRepository levelRepository;
	private final CourseRepository categoryRepository;
	private final QuestionRepository questionRepository;
	private final UserExamRepository userExamRepository;

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
	public ExamDto createExam(ExamRequest request) {
	    List<Question> questions = new ArrayList<>();

	    for (QuestionRequest questionRequest : request.getQuestions()) {
	        questions.add(addQuestionToExam(questionRequest));
	    }
	    Exam exam = new Exam();
	    exam.setName(request.getName());
	    exam.setDescription(request.getDescription());
	    exam.setExamdurationMinutes(request.getExamDurationMinute());
	    exam.setExamTotalMark(request.getExamTotalMark());
	    exam.setCourse(categoryRepository.findById(request.getCourseId()).orElseThrow(() -> new NotFoundException("Course not found")));
	    exam.setLevel(levelRepository.findById(request.getLevelId()).orElseThrow(() -> new NotFoundException("Level not found")));
	    exam.setNumberOfQuestionsToGenerate(request.getNoOfQuestion());
	    exam.setQuestions(questions);
	    examRepository.save(exam);
	    return examMapper.toDto(exam);
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
	public void updateExam(Long id, ExamRequest request) {
		try {
			Exam foundedExam = findExam(id);
			foundedExam.setName(request.getName());
			foundedExam.setDescription(request.getDescription());
			foundedExam.setExamdurationMinutes(request.getExamDurationMinute());
			foundedExam.setExamTotalMark(request.getExamTotalMark());
			foundedExam.setNumberOfQuestionsToGenerate(request.getNoOfQuestion());
			foundedExam.setCourse(categoryRepository.findById(request.getCourseId()).orElseThrow(()->new NotFoundException("Cousre not found")));
			foundedExam .setLevel(levelRepository.findById(request.getLevelId()).orElseThrow(()->new NotFoundException("Level not found")));
			examRepository.save(foundedExam);
		}catch(Exception e)
		{
			throw new AppException("something is wrong with updating exam");
		}
	}
	@Override
	public void updateQuestionWithExamId(Long id, List<QuestionRequest> request) {
		 try {
			 Exam foundedExam = findExam(id);
			    List<Question> existingQuestions = foundedExam.getQuestions();
			    List<Question> questionsToAdd = new ArrayList<>();
			    for (QuestionRequest questionRequest : request) {
			        if (!isQuestionAlreadyInExam(existingQuestions, questionRequest.getQuestion())) {
			            Question question = addQuestionToExam(questionRequest);
			            questionsToAdd.add(question);
			        }
			    }
			    existingQuestions.addAll(questionsToAdd);
			    foundedExam.setQuestions(existingQuestions);
			    examRepository.save(foundedExam);
		 }catch(Exception e)
		{
			throw new AppException("something is wrong with updating exam");
		}
	}
	private boolean isQuestionAlreadyInExam(List<Question> questions, String questionContent) {
	    return questions.stream()
	            .anyMatch(q -> q.getQuestion().equals(questionContent));
	}
	@Override
	public boolean setExamPublished(Long id, ExamPublishedRequest request) {
		boolean isPublished = request.isPublished();
		Exam foundedExam = findExam(id);
		if (isPublished) {
			int requiredQuestionCount = foundedExam.getNumberOfQuestionsToGenerate();
			int actualQuestionCount = examRepository.countQuestionsForExam(foundedExam.getId());
			if (actualQuestionCount < requiredQuestionCount) {
				throw new BadRequestException("Not enough questions to publish the exam.");
			}
		}
		else {
			Exam takenExam=userExamRepository.getExam();
			if(takenExam!=null)
			{
				throw new BadRequestException("Can't be unpublished because already has examinees.");
			}
		}
		foundedExam.setPublished(isPublished);
		foundedExam.setExamPublishDate(isPublished ? new Date() : null);
		examRepository.save(foundedExam);
		return isPublished;
	}
	@Override
	public List<QuestionDto> getRandomQuestionsForExam(ExamDto dto) {
		int no = dto.getNumberOfQuestionsToGenerate();
		List<QuestionDto> allQuestions = dto.getQuestions();
		Collections.shuffle(allQuestions);
		int numberOfQuestionsToTake = Math.min(no, allQuestions.size());
		List<QuestionDto> questionList = allQuestions.subList(0, numberOfQuestionsToTake);
		return questionList;
	}

	@Override
	public void deleteExam(Long id) {
		Exam foundedExam=examRepository.findById(id).orElseThrow(()->new NotFoundException("Exam not found"));
		if(foundedExam.isPublished()==true)
		{
			throw new BadRequestException("You can't be delete exam because the exam is already published");
		}
		examRepository.save(foundedExam);
	}

	
}
