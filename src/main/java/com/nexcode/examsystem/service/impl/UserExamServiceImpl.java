package com.nexcode.examsystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexcode.examsystem.mapper.UserExamMapper;
import com.nexcode.examsystem.model.dtos.UserExamDto;
import com.nexcode.examsystem.model.entities.Answer;
import com.nexcode.examsystem.model.entities.Exam;
import com.nexcode.examsystem.model.entities.Question;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.entities.UserAnswer;
import com.nexcode.examsystem.model.entities.UserExam;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.exception.NotFoundException;
import com.nexcode.examsystem.model.projections.UserExamHistoryProjection;
import com.nexcode.examsystem.model.requests.UserAnswerRequest;
import com.nexcode.examsystem.repository.ExamRepository;
import com.nexcode.examsystem.repository.QuestionRepository;
import com.nexcode.examsystem.repository.UserAnswerRepository;
import com.nexcode.examsystem.repository.UserExamRepository;
import com.nexcode.examsystem.repository.UserRepository;
import com.nexcode.examsystem.service.UserExamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserExamServiceImpl implements UserExamService {

	private final UserExamMapper userExamMapper;
	private final UserRepository userRepository;
	private final ExamRepository examRepository;
	private final UserExamRepository userExamRepository;
	private final UserAnswerRepository userAnswerRepository;
	private final QuestionRepository questionRepository;

	@Override
	public void createUserExam(Long userId, Long examId) {
		User foundedUser = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("user not found"));
		Exam foundedExam = examRepository.findById(examId).orElseThrow(() -> new BadRequestException("Exam not found"));
		UserExam userExam = new UserExam();
		userExam.setUser(foundedUser);
		userExam.setExam(foundedExam);
		userExam.setObtainedResult(0);
		userExam.setIsActive(true);
		userExamRepository.save(userExam);
	}

	@Override
	public UserExamDto findUserExamByUserAndExam(Long userId,Long examId, List<UserAnswerRequest> userAnswers) 
	{
		Exam foundedExam = examRepository.findById(examId).orElseThrow(() -> new BadRequestException("Exam not found"));
		UserExam foundedUserExam = userExamRepository.findByUserExam(userId, examId);
		foundedUserExam.setSubmittedTime(new Date());
		int markForEachQuestion = foundedExam.getExamTotalMark() / foundedExam.getNoOfQuestion();
		int obtainedMarks = calculateObtainedMarks(markForEachQuestion, userAnswers, foundedUserExam);

		foundedUserExam.setObtainedResult(obtainedMarks);
		int passingMark = foundedExam.getExamTotalMark() / 2;
		foundedUserExam.setIsPassFail(obtainedMarks >= passingMark);
		try {
			userExamRepository.save(foundedUserExam);
			return userExamMapper.toDto(foundedUserExam);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
		
	}

	public int calculateObtainedMarks(int mark, List<UserAnswerRequest> userAnswers, UserExam userExam) {
		int obtainedMarks = 0;
		List<UserAnswer> userAnswerList = new ArrayList<>();
		for (UserAnswerRequest a : userAnswers) {
			Long questionId = a.getQuestionId();
			Question question = questionRepository.findById(questionId)
					.orElseThrow(() -> new BadRequestException("question not found"));
			Answer correctAnswer = findCorrectAnswer(question);

			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setQuestion(question);
			userAnswer.setSelectedAnswer(a.getSelectedAnswer());
			userAnswer.setUserExam(userExam);
			userAnswerRepository.save(userAnswer);
			userAnswerList.add(userAnswer);
			if (correctAnswer != null && correctAnswer.getAnswer().equals(a.getSelectedAnswer())) {
				userAnswer.setIsSelectedAnswerCorrect(true);
				obtainedMarks += mark;
			} else {
				userAnswer.setIsSelectedAnswerCorrect(false);
			}

		}
		userExam.setUserAnswers(userAnswerList);
		return obtainedMarks;
	}

	private Answer findCorrectAnswer(Question question) {
		for (Answer answer : question.getAnswers()) {
			if (answer.isCorrectAnswer()) {
				return answer;
			}
		}
		return null;
	}
	@Override
	public List<UserExamHistoryProjection> getAllExamHistoryByUserId(String email) {
		User foundedUser=userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
		return userExamRepository.findAllExamHistoryByUserId(foundedUser.getId());
	}

	@Override
	public UserExamDto getEachExamAllHistory(String email, Long examId) {
		User foundedUser=userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
		return userExamMapper.toDto(userExamRepository.findByUserExam(foundedUser.getId(),examId));
	}

}
