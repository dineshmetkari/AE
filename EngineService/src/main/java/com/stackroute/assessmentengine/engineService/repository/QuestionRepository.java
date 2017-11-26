package com.stackroute.assessmentengine.engineService.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;
import com.stackroute.assessmentengine.engineService.exceptions.ResourceNotFoundException;


@Repository
@Transactional
public class QuestionRepository {
	private static final String KEY = "questions";
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, String, Question> hashOps;	
	  
	  
	  public void addquestion(Question question) throws ResourceNotFoundException {
		  String key=question.getUserid()+"Q";
		  System.out.println("Storing data in cahe for"+key+"for id"+question.getId());
		  try {
		  hashOps.put(key, question.getId(), question);
		  }
		  catch(Exception e) {
			  throw new ResourceNotFoundException("can't get it from jedis pool");
		  }
	  }
	  
	  public Question getquestion(Question question) throws ResourceNotFoundException {
		  String key=question.getUserid()+"Q";
		  System.out.println("getting data from cahe for"+key+"and for qid:"+question.getNextQuestion());
		  try {
		  return hashOps.get(key, question.getNextQuestion());
		  }
		  catch(Exception e) {
			  throw new ResourceNotFoundException("can't get it from jedis pool");
		  }
	  }
	  

}
