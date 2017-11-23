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

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.web.client.RestTemplate;
import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;
import com.stackroute.assessmentengine.evaluationmanager.service.EvaluationManagerService;

import ch.qos.logback.core.net.SyslogOutputStream;

//@Component
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	KafkaProducer producer;
	@Autowired
	EvaluationManagerService evaluationmanager;

//	
	@KafkaListener(topics="${kafka.topic.json}")
	
    public void questionrecived(QuestionBean questionBean) {
		
		log.info("Question for Evaluation:", questionBean.toString());
		producer.sendQuestion(questionBean);
		
//        final String uri = "http://localhost:8086/Statemanager/v0.1/getall/"+user.getStudentId();
//        RestTemplate restTemplate = new RestTemplate();
//        QuestionBean[] forNow = restTemplate.getForObject(uri, QuestionBean[].class);
//        List<QuestionBean> searchList= Arrays.asList(forNow);
//        System.out.println("============================"+searchList);
//        String flag="true";
//        for(QuestionBean questionBean:searchList) {
//        	if(!flag.equalsIgnoreCase(questionBean.getIsEvaluated())) {
//        		System.out.println("sending Question");
//        		producer.sendQuestion(questionBean);
//        	}
//         }
     
     }
	
    @KafkaListener(topics="${kafka.topic.json2}")
    
    public void examstatus(QuestionBean q)   {
    	
    	evaluationmanager.users(q.getStudentId());
    	
//    	 String flag="true";
//    	 System.out.println(q.toString());
//    	log.info("Exam status is closed for UserId:", q.getStudentId());
//		Set<String> userset=new HashSet<>();
//		userset.add(q.getStudentId());

//		 final String uri = "http://localhost:8086/Statemanager/v0.1/getall/"+q.getStudentId();
//	        RestTemplate restTemplate1 = new RestTemplate();
//	        QuestionBean[] forNow = restTemplate1.getForObject(uri, QuestionBean[].class);
//	        List<QuestionBean> searchList= Arrays.asList(forNow);
//	        System.out.println("getting from redis cache:::"+searchList);
//	        int i=0;
//	       
//	        for(QuestionBean questionBeans:searchList) {
//	        	if(flag.equalsIgnoreCase(questionBeans.getIsEvaluated())) {
//	        		System.out.println(questionBeans.getIsEvaluated());
//	        		System.out.println("in loop");
//	        		i++;
//	        	}
//	         }
//	     System.out.println("list sizeis"+searchList.size()+"I value"+i);
//	     
//		if(searchList.size()==i) {
//			System.out.println("Evaluation completed for user and sending it to resultset ");
//		        
//		        UserResultBean userexam = new UserResultBean();
//		
//		       userexam.setUserId(q.getStudentId());
//		       userexam.setResultBean(searchList);
//		
//		     producer.sendUserExam(userexam);
//		
//		    }
//		else {
//				userset.add(q.getStudentId());
//				for(QuestionBean questionBean:searchList) {
//		        	if(!flag.equalsIgnoreCase(questionBean.getIsEvaluated())) {
//		        		System.out.println("-----------"+questionBean);
//		        		producer.sendQuestion(questionBean);
//		        	}
//		         }
//				
//		}
/*System.out.println(userset.size()+"//////////////////////////////////////////");
		RestTemplate restTemplate1 = new RestTemplate();
		while(userset.size()>0) {
			
			
			for(String user:userset) {
					String uri_getall = "http://localhost:8086/Statemanager/v0.1/getall/"+user;
			        QuestionBean[] userquestions = restTemplate1.getForObject(uri_getall, QuestionBean[].class);
			        List<QuestionBean> questionList= Arrays.asList(userquestions);
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
					
					     
					       producer.sendUserExam(userexam);
					      userset.remove(user);
					      
			        	
			        }
			 }//end of for each for set
			
		}//end of while
		System.out.println(userset.size());
		*/
    }//end of examstatus method
}