package com.stackroute.assessmentengine.evaluationmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;
import com.stackroute.assessmentengine.evaluationmanager.exception.KafkaUnavialableException;
import com.stackroute.assessmentengine.evaluationmanager.message.KafkaProducer;

@Service
public class EvaluationManagerService {
	@Autowired
	KafkaProducer producer;

	public void users(String userid) {
		
		 String flag="true";
		 List<QuestionBean> questionList=new ArrayList<>();
    	
    	System.out.println("Exam status is closed for UserId:"+ userid);
		Set<String> userset=new HashSet<>();
		userset.add(userid);

        System.out.println(userset.size()+"//////////////////////////////////////////");
		RestTemplate restTemplate1 = new RestTemplate();
		while(userset.size()>0) {
			
			
			for(String user:userset) {
				try {
					String uri_getall = "http://localhost:8086/Statemanager/v0.1/getall/"+user;
			    
					QuestionBean[] userquestions = restTemplate1.getForObject(uri_getall, QuestionBean[].class);
					  questionList= Arrays.asList(userquestions);
				
				       
				        int j=0;
				        System.out.println(questionList);
				        for(QuestionBean questions:questionList) {
				        	if(flag.equalsIgnoreCase(questions.getIsEvaluated())) {
				        		
				        		j++;
				        	}
				         }
				        System.out.println("list sizeis"+questionList.size()+"I value"+j);
				        if(questionList.size()==j) {
				        	
				        	System.out.println("Evaluation completed for user and sending it to resultset ");
						      
						        UserResultBean userexam = new UserResultBean();
						
						       userexam.setUserId(user);
						       userexam.setResultBean(questionList);
						
						     try {
						       producer.sendUserExam(userexam);
						     }
						     catch(KafkaUnavialableException e) {
						    	 System.out.println("Kafka server is Down");
						     }
						      userset.remove(user);
				        }
				}
				catch(Exception e) {
					System.out.println("can't get data from statemanager");
				}
			 }//end of for each for set
			
		}//end of while
		
		System.out.println(userset.size());
	}
}
