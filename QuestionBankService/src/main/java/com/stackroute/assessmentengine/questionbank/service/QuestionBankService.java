package com.stackroute.assessmentengine.questionbank.service;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.AggregationOutput;
import com.mongodb.DBCollection;
import com.stackroute.assessmentengine.questionbank.domain.QuestionBank;
import com.stackroute.assessmentengine.questionbank.exception.CustomExceptions;

public interface QuestionBankService {
	public List getallquestions() throws CustomExceptions;
	public QuestionBank getquestion(String id) throws CustomExceptions;
	public QuestionBank addquestion(QuestionBank questionBank);
	public QuestionBank updatequestion(String id,QuestionBank questionBank);
	public String deletequestion(String id);
	
	public List getSpecificquestions(String subject,String topic,String level,String complexity,String questionType,String num);
	public List getbysubject(String subject);
	
}
