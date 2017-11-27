package com.stackroute.assessmentengine.questionbank.domain;

import java.util.ArrayList;
import java.util.Date;

import com.mongodb.BasicDBObject;

import java.util.*;

public class QuestionList {
	private String id;
	private String question;
	private List<Options> options;
	private String correctAnswer;
	private String marksAlloted;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getMarksAlloted() {
		return marksAlloted;
	}

	public void setMarksAlloted(String marksAlloted) {
		this.marksAlloted = marksAlloted;
	}
	
	public QuestionList(String id, String question, List<Options> options, String correctAnswer, String marksAlloted) {
		super();
		
		this.id = id;
		this.question = question;
		this.options = options;
		this.correctAnswer = correctAnswer;
		this.marksAlloted = marksAlloted;
	}
	

	public QuestionList() {
		super();
		// TODO Auto-generated constructor stub
		this.options=new ArrayList<>();
	}

	
	
	
	

}
