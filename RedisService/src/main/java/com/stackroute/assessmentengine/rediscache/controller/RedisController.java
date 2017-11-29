package com.stackroute.assessmentengine.rediscache.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.domain.QuestionBean1;
import com.stackroute.assessmentengine.rediscache.exceptions.RedisServerDownException;
import com.stackroute.assessmentengine.rediscache.repository.QuestionRepository;



@RestController
public class RedisController {
	@Autowired
	QuestionRepository questionRepository;
//	@GetMapping("/redis")
//	public  void questioncrud() {
//
//			System.out.println("--Example of RedisTemplate for ListOperations--");
//			List<String> list=new ArrayList();
//			list.add("option1");
//			list.add("option2");
////			QuestionBean1 questionBean=new QuestionBean1("sas","user6", "1", "rwrwrwrwrrw",
////					list, "hasha", "asha", "mcq",
////					"java", "l2", "hard","3","sdsa",
////					"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
////					"4");
////			QuestionBean1 questionBean1=new QuestionBean1("sas","user6", "2", "Question1",
////					list, "hasha", "asha", "mcq",
////					"java", "l2", "hard","3","sdsa",
////					"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
////					"4");
//			QuestionBean questionBean=new QuestionBean("asas","user1","1","Question1",
//				list,"option1", "option1","mcq","java","l1","easy","2","10",
//				"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
//				null,"false");
//		   QuestionBean questionBean1=new QuestionBean("asas","user1","2","Question1",
//				list,"option1", "option1","mcq","java","l1","easy","2","10",
//				"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
//				null,"false");
////		   QuestionBean questionBean2=new QuestionBean("asas","user1","3","Question1",
////					list,"option1", "option1","mcq","java","l1","easy","2","10",
////					"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
////					null,"false");
////		  questionRepository.addquestion1(questionBean,questionBean.getStudentId());
////		  questionRepository.addquestion1(questionBean1,questionBean1.getStudentId());
////		  questionRepository.updatequestion1(questionBean);
//		  questionRepository.addquestion(questionBean1,questionBean1.getStudentId());
//		  questionRepository.addquestion(questionBean,questionBean.getStudentId());
//	      
//		   System.out.println("Number of friends: " + questionRepository.getNumberOfquestions(questionBean.getStudentId()));
////		   System.out.println("Number of friends: " + questionRepository.getquestion1(questionBean.getQuestionId(),questionBean.getStudentId()));
////		   System.out.println("Number of questions: " + questionRepository.getAllquestions1(questionBean.getStudentId())); 
//		  
////		   questionRepository.addquestion(questionBean1,questionBean1.getStudentId());
////	      
////		   System.out.println("Number of friends: " + questionRepository.getNumberOfquestions(questionBean1.getStudentId()));
////		   System.out.println("Number of friends: " + questionRepository.getquestion(questionBean1.getQuestionId(),questionBean1.getStudentId()));
////		   System.out.println("Number of friends: " + questionRepository.getAllquestions(questionBean1.getStudentId())); 
////		   System.out.println("deleted:"+questionRepository.deletequestions(questionBean1.getStudentId(),questionBean1.getQuestionId()));
////		   System.out.println("Number of friends: " + questionRepository.getAllquestions(questionBean1.getStudentId())); 
////			  
//	
//	}
	@GetMapping("/Statemanager/v0.1/getall/{userId}")
	public List< QuestionBean> getAll(@PathVariable String userId) throws RedisServerDownException{
		try {
		Map<String, QuestionBean> questions=questionRepository.getAllquestions(userId);
		List<QuestionBean> list = new ArrayList<QuestionBean>(questions.values());
		return list;
		}
		catch(RedisServerDownException e) {
			throw new RedisServerDownException("Redis server is down");
		}

	}
	
	@DeleteMapping("/Statemanager/v0.1/deleteByID/{userId}")
	public void deleteAll(@PathVariable String userId) throws RedisServerDownException{
		try {
		Map<String, QuestionBean> questions=questionRepository.getAllquestions(userId);
		List<QuestionBean> list = new ArrayList<QuestionBean>(questions.values());
		for(QuestionBean questionbean:list) {
			questionRepository.deletequestions(userId, questionbean.getQuestionId());
		}
		
	   }
		catch(RedisServerDownException e) {
			throw new RedisServerDownException("Redis server is down");
		}
	}
}
