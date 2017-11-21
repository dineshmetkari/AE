package com.stackroute.assessmentengine.evaluationmanager.message;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	KafkaProducer producer;
	private CountDownLatch latch = new CountDownLatch(1);
	
	public CountDownLatch getLatch() {
	    return latch;
	  }
	
	@KafkaListener(topics="${kafka.topic.json}")
	
    public void questionrecived(String userId) {
		
		log.info("received content = '{}'", userId);
		latch.countDown();
        final String uri = "http://localhost:8086/Statemanager/v0.1/getall/"+userId;
        RestTemplate restTemplate = new RestTemplate();
        QuestionBean[] forNow = restTemplate.getForObject(uri, QuestionBean[].class);
        List<QuestionBean> searchList= Arrays.asList(forNow);
        System.out.println("============================"+searchList);
        String flag="true";
        for(QuestionBean questionBean:searchList) {
        	if(!flag.equalsIgnoreCase(questionBean.getIsEvaluated())) {
        		System.out.println("sending Question");
        		producer.sendQuestion(questionBean);
        	}
         }
     
       }
	
    @KafkaListener(topics="${kafka.topic.json2}")
	
    public void examstatus(QuestionBean q) throws InterruptedException {
    	 String flag="true";
    	log.info("received content = '{}'", q.getStudentId());
		Set<String> userset=new HashSet<>();

		 final String uri = "http://localhost:8086/Statemanager/v0.1/getall/"+q.getStudentId();
	        RestTemplate restTemplate1 = new RestTemplate();
	        QuestionBean[] forNow = restTemplate1.getForObject(uri, QuestionBean[].class);
	        List<QuestionBean> searchList= Arrays.asList(forNow);
	        int i=0;
	       
	        for(QuestionBean questionBeans:searchList) {
	        	if(flag.equalsIgnoreCase(questionBeans.getIsEvaluated())) {
	        		i++;
	        	}
	         }
	     
		if(searchList.size()==i) {
			
		        
		        UserResultBean userexam = new UserResultBean();
		
		       userexam.setUserId(q.getStudentId());
		       userexam.setResultBean(searchList);
		
		     producer.sendUserExam(userexam);
		
		    }
		else {
				userset.add(q.getStudentId());
				for(QuestionBean questionBean:searchList) {
		        	if(!flag.equalsIgnoreCase(questionBean.getIsEvaluated())) {
		        		System.out.println("-----------"+questionBean);
		        		producer.sendQuestion(questionBean);
		        	}
		         }
				
		}
		System.out.println(userset.size()+"//////////////////////////////////////////");
		while(userset.size()>0) {
			Thread.sleep(1000);
			for(String user:userset) {
					String uri_getall = "http://localhost:8086/Statemanager/v0.1/getall/"+user;
			        QuestionBean[] userquestions = restTemplate1.getForObject(uri, QuestionBean[].class);
			        List<QuestionBean> questionList= Arrays.asList(userquestions);
			        int j=0;
			        for(QuestionBean questions:questionList) {
			        	if(flag.equalsIgnoreCase(questions.getIsEvaluated())) {
			        		j++;
			        	}
			         }
			        if(questionList.size()==j) {
			        	
			        	
					        UserResultBean userexam = new UserResultBean();
					
					       userexam.setUserId(user);
					       userexam.setResultBean(questionList);
					
					     
					       producer.sendUserExam(userexam);
					      userset.remove(user);
					      
			        	
			        }
			        else {
			        	for(QuestionBean q1:questionList) {
			        		if(!flag.equalsIgnoreCase(q1.getIsEvaluated())) {
			        			System.out.println("in set-----++++++++");
			        			userset.remove(user);
			        			producer.sendQuestion(q);
			        		}
			        	}	
			        }
			 }//end of for each for set
		}//end of while
		
		System.out.println(userset.size());
    }//end of examstatus method
}