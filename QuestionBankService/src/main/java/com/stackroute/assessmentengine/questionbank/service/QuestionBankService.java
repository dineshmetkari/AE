package com.stackroute.assessmentengine.questionbank.service;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.AggregationOutput;
import com.mongodb.DBCollection;
import com.stackroute.assessmentengine.questionbank.domain.QuestionBank;

public interface QuestionBankService {
	public List getallquestions();
	public QuestionBank getquestion(String id);
	public QuestionBank addquestion(QuestionBank questionBank);
	public QuestionBank updatequestion(String id,QuestionBank questionBank);
	public String deletequestion(String id);
	
	public List getSpecificquestions(String subject,String topic,String level,String complexity,String questionType,String num);
	
}
