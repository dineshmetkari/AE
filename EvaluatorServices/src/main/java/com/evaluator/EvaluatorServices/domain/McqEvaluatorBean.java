package com.evaluator.EvaluatorServices.domain;

import java.util.ArrayList;
import java.util.List;

public class McqEvaluatorBean {
	
	private String examId;
	private String studentId;
	private String questionId;
	private String question;
	private List<McqOptionsBean> mcqOptions;
	private String correctAnswer;
	private String userAnswer;
	private String questionType;
	private String subject;
	private String level;
	private String complexity;
	private String marksAlloted;
	private String timeTakenInSeconds;
	private String questionStartTime;
	private String questionEndTime;
	private String examStartTime;
	private String examEndTime;
	private String marksAttained;
	
	
	public McqEvaluatorBean() {
		super();
		this.mcqOptions = new ArrayList<>();
	}

	

	public McqEvaluatorBean(String examId, String studentId, String questionId, String question,
			List<McqOptionsBean> mcqOptions, String correctAnswer, String userAnswer, String questionType,
			String subject, String level, String complexity, String marksAlloted, String timeTakenInSeconds,
			String questionStartTime, String questionEndTime, String examStartTime, String examEndTime) {
		super();
		this.examId = examId;
		this.studentId = studentId;
		this.questionId = questionId;
		this.question = question;
		this.mcqOptions = mcqOptions;
		this.correctAnswer = correctAnswer;
		this.userAnswer = userAnswer;
		this.questionType = questionType;
		this.subject = subject;
		this.level = level;
		this.complexity = complexity;
		this.marksAlloted = marksAlloted;
		this.timeTakenInSeconds = timeTakenInSeconds;
		this.questionStartTime = questionStartTime;
		this.questionEndTime = questionEndTime;
		this.examStartTime = examStartTime;
		this.examEndTime = examEndTime;
	}
	
	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<McqOptionsBean> getMcqOptions() {
		return mcqOptions;
	}

	public void setMcqOptions(List<McqOptionsBean> mcqOptions) {
		this.mcqOptions = mcqOptions;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public String getMarksAlloted() {
		return marksAlloted;
	}

	public void setMarksAlloted(String marksAlloted) {
		this.marksAlloted = marksAlloted;
	}

	public String getTimeTakenInSeconds() {
		return timeTakenInSeconds;
	}

	public void setTimeTakenInSeconds(String timeTakenInSeconds) {
		this.timeTakenInSeconds = timeTakenInSeconds;
	}
	
	public String getQuestionStartTime() {
		return questionStartTime;
	}

	public void setQuestionStartTime(String questionStartTime) {
		this.questionStartTime = questionStartTime;
	}

	public String getQuestionEndTime() {
		return questionEndTime;
	}

	public void setQuestionEndTime(String questionEndTime) {
		this.questionEndTime = questionEndTime;
	}

	public String getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}

	public String getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(String examEndTime) {
		this.examEndTime = examEndTime;
	}

	public String getMarksAttained() {
		return marksAttained;
	}

	public void setMarksAttained(String marksAttained) {
		this.marksAttained = marksAttained;
	}

	@Override
	public String toString() {
		return "EvaluatorBean [examId=" + examId + ", studentId=" + studentId + ", questionId=" + questionId
				+ ", question=" + question + ", mcqOptions=" + mcqOptions + ", correctAnswer=" + correctAnswer
				+ ", userAnswer=" + userAnswer + ", questionType=" + questionType + ", subject=" + subject + ", level="
				+ level + ", complexity=" + complexity + ", marksAlloted=" + marksAlloted + ", timeTakenInSeconds="
				+ timeTakenInSeconds + ", questionStartTime=" + questionStartTime + ", questionEndTime="
				+ questionEndTime + ", examStartTime=" + examStartTime + ", examEndTime=" + examEndTime
				+ ", marksAttained=" + marksAttained + "]";
	}
		
}
