package com.stackroute.assessmentengine.questionbank.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.math.util.MultidimensionalCounter.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.stackroute.assessmentengine.questionbank.config.SpringMongoConfig;
import com.stackroute.assessmentengine.questionbank.domain.QuestionBank;
import com.stackroute.assessmentengine.questionbank.domain.QuestionList;
import com.stackroute.assessmentengine.questionbank.exception.CustomExceptions;
import com.stackroute.assessmentengine.questionbank.repository.QuestionBankMongoRepository;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	QuestionBankMongoRepository mongoRepository;

	@Override
	public List getallquestions() throws CustomExceptions {
		List<QuestionBank> l=mongoRepository.findAll();
		if(l.isEmpty()) {
			throw new CustomExceptions("No questions are existed in database");
		}
		else{
			return l;
		}
		
	}

	@Override
	public QuestionBank getquestion(String id) throws CustomExceptions{
		QuestionBank questionBank=mongoRepository.findOne(id);
		
			return questionBank;
		
		
		
	}

	@Override
	public QuestionBank addquestion(QuestionBank questionBank) {
		return mongoRepository.save(questionBank);
	}

	@Override
	public QuestionBank updatequestion(String id, QuestionBank questionBank) {
		return mongoRepository.save(questionBank);
	}

	@Override
	public String deletequestion(String id) {
		mongoRepository.delete(id);
		return "";
	}

	@Override
	public List getSpecificquestions(String subject,String topic,String level,String complexity,String questionType,String num) {
		int n=Integer.parseInt(num);
		Query query11 = new Query();
		query11.addCriteria(Criteria.where("subjectLists.subject").in(subject)
				.andOperator(Criteria.where("subjectLists.topicList.topic").in(topic)
				.andOperator(Criteria.where("subjectLists.topicList.levelList.level").in(level)
				.andOperator(Criteria.where("subjectLists.topicList.levelList.complexityList.complexity").in(complexity)
				.andOperator(Criteria.where("subjectLists.topicList.levelList.complexityList.questionTypeList.questionType").in(questionType))))));
	
		List<QuestionBank> userTest11 = mongoOperation.find(query11, QuestionBank.class);
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"+userTest11.size());
		System.out.println("query11 - " + query11.toString());
		ArrayList questions=new ArrayList<>();
		
		Map<Integer,QuestionBank> list=new HashMap<>();
		Integer m=0;
		
		for (QuestionBank questionBank : userTest11) {
			
			list.put(m, questionBank);
			m++;
			System.out.println("userTest11 - " + questionBank);
		}
		
		
		final int[] ints = new Random().ints(0, list.size()).distinct().limit(n).toArray();
		for(int i=0;i<ints.length;i++) {
			System.out.println("DDDDDDDDDDDDDDDDDDDDD"+ints[i]);
		}
		
		
		QuestionBank q;
		for(int j=0;j<ints.length;j++) {
			 q=list.get(ints[j]);
			 questions.add(q);
			
		}
		return questions;
	}

}
