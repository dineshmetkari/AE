package com.stackroute.assessmentengine.rediscache.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.domain.QuestionBean1;

@Repository
@Transactional
public class QuestionRepository {
	private static final String KEY = "questions";
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, String, QuestionBean> hashOps;	
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, String, QuestionBean1> hashOps1;
	  public Map<String, QuestionBean1> getAllquestions1(String studentid) {
		  return hashOps1.entries(studentid);
	  }
	  public QuestionBean1 getquestion1(String id,String studentid) {
		  return hashOps1.get(studentid, id);
	  }
	  public void addquestion1(QuestionBean1 questionBean,String studentid) {
		  hashOps1.putIfAbsent(studentid, questionBean.getQuestionId(), questionBean);
	  }
	  public void updatequestion1(QuestionBean1 questionBean) {
		  hashOps1.put(questionBean.getStudentId(), questionBean.getQuestionId(), questionBean);
	  }	
	  
	  
	  public void addquestion(QuestionBean questionBean,String studentid) {
		  hashOps.put(studentid, questionBean.getQuestionId(), questionBean);
	  }
	  public void updatequestion(QuestionBean questionBean) {
		  hashOps.put(KEY, questionBean.getQuestionId(), questionBean);
	  }	  
	  public QuestionBean getquestion(String id,String studentid) {
		  return hashOps.get(studentid, id);
	  }
	  public long getNumberOfquestions(String studentid) {
		  return hashOps.size(studentid);
	  }
	  public Map<String, QuestionBean> getAllquestions(String studentid) {
		  return hashOps.entries(studentid);
	  }
	  public long deletequestions(String id,String qid) {
		  return hashOps.delete(id,qid);
		  
	  }	  		

}
