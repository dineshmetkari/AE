package com.stackroute.assessmentengine.engineService.domain;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements Serializable{
	private String examStatus;
	

	private String id;
	private String userid;
	private String topic;
	private String subject;
	private String level;
	private String complexity;
	private String questionType;
	private String question;
	private String correctAnswer;
	private String selectedAnswer;
	private String marksAllotted;
	private String StartTime;
	private String nextQuestion;
	private String options[]= {"option1","option2","option3"};
	
	public Question() {
		
	}
  
	public Question(String status,String id,String nextQuestion,String selectedAnswer, String userid, String topic, String subject, String level, String complexity,
			String questionType, String question, String correctAnswer, String marksAllotted, String startTime,
			String[] options) {
		super();
		this.examStatus=status;
		this.selectedAnswer=selectedAnswer;
		this.nextQuestion=nextQuestion;
		this.id = id;
		this.userid = userid;
		this.topic = topic;
		this.subject = subject;
		this.level = level;
		this.complexity = complexity;
		this.questionType = questionType;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.marksAllotted = marksAllotted;
		StartTime = startTime;
		this.options = options;
	}
	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}
	
	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public String getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(String nextQuestion) {
		this.nextQuestion = nextQuestion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
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
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getMarksAllotted() {
		return marksAllotted;
	}
	public void setMarksAllotted(String marksAllotted) {
		this.marksAllotted = marksAllotted;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Question [examStatus=" + examStatus + ", id=" + id + ", userid=" + userid + ", topic=" + topic
				+ ", subject=" + subject + ", level=" + level + ", complexity=" + complexity + ", questionType="
				+ questionType + ", question=" + question + ", correctAnswer=" + correctAnswer + ", selectedAnswer="
				+ selectedAnswer + ", marksAllotted=" + marksAllotted + ", StartTime=" + StartTime + ", nextQuestion="
				+ nextQuestion + ", options=" + Arrays.toString(options) + "]";
	}

	
	

}
