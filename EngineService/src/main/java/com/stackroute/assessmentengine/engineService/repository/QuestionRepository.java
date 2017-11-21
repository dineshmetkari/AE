package com.stackroute.assessmentengine.engineService.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;


@Repository
@Transactional
public class QuestionRepository {
	private static final String KEY = "questions";
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, String, Question> hashOps;	
	  
	  
	  public void addquestion(Question question) {
		  String key=question.getUserid()+"Q";
		  hashOps.put(key, question.getId(), question);
	  }
	  
	  public Question getquestion(Question question) {
		  String key=question.getUserid()+"Q";
		  return hashOps.get(key, question.getNextQuestion());
	  }
	  

}
