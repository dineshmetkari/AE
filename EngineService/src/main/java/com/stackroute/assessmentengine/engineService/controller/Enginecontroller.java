package com.stackroute.assessmentengine.engineService.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;
import com.stackroute.assessmentengine.engineService.exceptions.KafkaUnavialableException;
import com.stackroute.assessmentengine.engineService.message.KafkaProducer;
import com.stackroute.assessmentengine.engineService.repository.QuestionRepository;



//Engine Controller will maintain the session of users Exam and sent requested Questions to user

@RestController
public class Enginecontroller {
	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	KafkaProducer producer;
	
	@Autowired
	QuestionRepository questionRepository;
	
	int count=0;
	
	static List<Question> questions=new ArrayList<>();
	static Map<Integer,Question> questionPaper;


	@MessageMapping("/questions/{userId}")
    
    public void questionData(@DestinationVariable String userId,Question question)  {
		
		System.out.println("user:"+userId);
     
		System.out.println("recieved from user"+question.toString());
		Enginecontroller ec=new Enginecontroller();
		
		if(count<=1) {
			ec.getall();
			count++;
		}
        if(questions.size()!=0)
        {
		System.out.println("set of questionPaper"+questionPaper);
		Question nextQuestion=null;
		
		try {
			
		questionRepository.addquestion(question);
		
		nextQuestion=questionRepository.getquestion(question); 
		}
		catch(Exception e) {
			System.out.println(e);
		}	
		
		QuestionBean questionBean=new QuestionBean();
		
		Integer qno=Integer.parseInt(question.getId());
		System.out.println("nextquestion no:  "+qno);
		Question current=questionPaper.get(qno);
		
		
		questionBean.setStudentId(question.getUserid());
		System.out.println("Question ID"+question.getId());
		questionBean.setQuestionId(question.getId());
		questionBean.setQuestion(current.getQuestion());
		List<String> optionsList1=Arrays.asList(current.getOptions());
		questionBean.setOptions(optionsList1);
		questionBean.setCorrectAnswer(current.getCorrectAnswer());
		questionBean.setUserAnswer(question.getSelectedAnswer());
		questionBean.setQuestionType(current.getQuestionType());
		questionBean.setSubject(current.getSubject());
		questionBean.setComplexity(current.getComplexity());
		questionBean.setQuestionStartTime(question.getStartTime());
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String endtime = dateFormat1.format(date);
		questionBean.setQuestionEndTime(endtime);
		questionBean.setMarksAllotted(current.getMarksAllotted());
		questionBean.setLevel(current.getLevel());
		
		
		QuestionBean questionBean1=new QuestionBean();
		
		
        
        String status="Close";
        
      try {
    	  if(status.equalsIgnoreCase(question.getExamStatus()))
          {
          	try {
          		producer.sendQuestion(questionBean);
          		Thread.sleep(3000);
          		producer.sendQuestion1(questionBean);
      	    }
      	    catch(Exception e) {
      	    	throw new KafkaUnavialableException("Kafka server is down");
      	    }
       	   
       	
          }
    	  else {
		
        	if(nextQuestion!=null) {
        		
        		System.out.println("from cache========================");
        		Integer n=Integer.parseInt(question.getNextQuestion());
        		System.out.println("nextquestion no:  "+n);
        		Question next=questionPaper.get(n);
        		
        		
        		questionBean1.setNoOfQuestions(String.valueOf(questionPaper.size()));
                System.out.println("NextQuestion::::::::::::"+next);
                questionBean1.setMsg("Exam Started");
                questionBean1.setQuestionId(String.valueOf(next.getId()));
                questionBean1.setQuestion(next.getQuestion());
                questionBean1.setQuestionType(next.getQuestionType());
                questionBean1.setMarksAllotted(next.getMarksAllotted());
                questionBean1.setSubject(next.getSubject());
                questionBean1.setComplexity(next.getComplexity());
             //   questionBean1.setCorrectAnswer(next.getCorrectAnswer());
                questionBean1.setUserAnswer(nextQuestion.getSelectedAnswer());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date1 = new Date();
                String starttime = dateFormat.format(date1);
                questionBean1.setQuestionStartTime(starttime);
                questionBean1.setLevel(next.getLevel());
                List<String> optionsList=Arrays.asList(next.getOptions());
                questionBean1.setOptions(optionsList);
                System.out.println("hgashgafsgshgashgahgs-------ok");
          	    simpMessagingTemplate.convertAndSend("/topic/question/"+question.getUserid() ,questionBean1);
          	    try {
          	    producer.sendQuestion(questionBean);
          	    }
          	    catch(Exception e) {
          	    	throw new KafkaUnavialableException("Kafka server is down");
          	    }
    			
    		}
        	else {
        		System.out.println("from map========================");
        		
        		Integer n=Integer.parseInt(question.getNextQuestion());
        		System.out.println("nextquestion no:  "+n);
        		Question next=questionPaper.get(n);
        		
        		
        		questionBean1.setNoOfQuestions(String.valueOf(questionPaper.size()));
                System.out.println("NextQuestion::::::::::::"+next);
                questionBean1.setMsg("Exam Started");
                questionBean1.setQuestionId(String.valueOf(next.getId()));
                questionBean1.setQuestion(next.getQuestion());
                questionBean1.setQuestionType(next.getQuestionType());
                questionBean1.setMarksAllotted(next.getMarksAllotted());
                questionBean1.setSubject(next.getSubject());
                questionBean1.setComplexity(next.getComplexity());
             //   questionBean1.setCorrectAnswer(next.getCorrectAnswer());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date1 = new Date();
                String starttime = dateFormat.format(date1);
                questionBean1.setQuestionStartTime(starttime);
                questionBean1.setLevel(next.getLevel());
                List<String> optionsList=Arrays.asList(next.getOptions());
                questionBean1.setOptions(optionsList);
        	    
	        	  simpMessagingTemplate.convertAndSend("/topic/question/"+question.getUserid() ,questionBean1);
	        	  try {
	            	    producer.sendQuestion(questionBean);
	            	    }
	            	    catch(Exception e) {
	            	    	throw new KafkaUnavialableException("Kafka server is down");
	            	    }
        	}
        
        }
        
        
        	
        }
        
      
      catch(KafkaUnavialableException e) {
    	  System.out.println(e);
    	  
      }
        }
	      else {
	      	System.out.println("else block");
	      	simpMessagingTemplate.convertAndSend("/topic/question/"+question.getUserid() , new QuestionBean("Exam not yet started"));
	      	
	      }
		}
    
   // @KafkaListener(topics = "${kafka.topic.json}")
    private static void getall()
    {
        final String uri = "http://172.23.238.217:8074/all";
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("in data");
        Question[] forNow = restTemplate.getForObject(uri, Question[].class);
       
        List<Question> searchList= Arrays.asList(forNow);
        Integer i=1;
        questionPaper=new HashMap<>();
        for(Question q:searchList) {
        	q.setId(String.valueOf(i));
        	q.setMarksAllotted("1");
        	questionPaper.put(i, q);
        	i++;
        }
//    	Integer i=1;
//    	String opti[]= {"a","b","c","d"};
//    	 List<Question> searchList= new ArrayList<Question>();
//    	 searchList.add(new Question("","","","", "", "java","java","l", "a",
//    				"mcq", "Quest", "asasas", "2", "",
//    				 opti));
//    	 searchList.add(new Question("","","","", "", "jsva","java","l", "a",
// 				"mcq", "Quest", "asasas", "2", "",
// 				 opti));
//    	 questionPaper=new HashMap<>();
//       for(Question q:searchList) {
//       	q.setId(String.valueOf(i));
//       	q.setMarksAllotted("1");
//       	questionPaper.put(i, q);
//       	i++;
//       }
    	 
        questions=searchList;
        System.out.println(searchList);
       }

}
		
