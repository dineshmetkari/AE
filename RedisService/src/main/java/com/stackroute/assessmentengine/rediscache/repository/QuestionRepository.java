package com.stackroute.assessmentengine.rediscache.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.domain.QuestionBean1;
import com.stackroute.assessmentengine.rediscache.exceptions.RedisServerDownException;

@Repository
@Transactional
public class QuestionRepository {
	private static final String KEY = "questions";
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, String, QuestionBean> hashOps;	
	  
	  public void addquestion(QuestionBean questionBean,String studentid) throws RedisServerDownException {
		  try {
			  hashOps.put(studentid, questionBean.getQuestionId(), questionBean);
		  }
		 catch(Exception e) {
			 throw new RedisServerDownException("Redis server is Down");
		 }
	  }
	  public void updatequestion(QuestionBean questionBean) throws RedisServerDownException {
		  try {
		  hashOps.put(KEY, questionBean.getQuestionId(), questionBean);
		  }
		  catch(Exception e) {
				 throw new RedisServerDownException("Redis server is Down");
			 }
	  }	  
	  public QuestionBean getquestion(String id,String studentid) throws RedisServerDownException {
		  try {
		  return hashOps.get(studentid, id);
		  }
		  catch(Exception e) {
				 throw new RedisServerDownException("Redis server is Down");
			 }
	  }
	  public long getNumberOfquestions(String studentid) throws RedisServerDownException {
		  try {
		  return hashOps.size(studentid);
		  }
		  catch(Exception e) {
				 throw new RedisServerDownException("Redis server is Down");
			 }
	  }
	  public Map<String, QuestionBean> getAllquestions(String studentid) throws RedisServerDownException {
		  try {
		  return hashOps.entries(studentid);
		  }catch(Exception e) {
				 throw new RedisServerDownException("Redis server is Down");
			 }
	  }
	  public long deletequestions(String id,String qid) throws RedisServerDownException {
		  try {
		  return hashOps.delete(id,qid);
		  }
		  catch(Exception e) {
				 throw new RedisServerDownException("Redis server is Down");
			 }
		  
	  }	  		

}
