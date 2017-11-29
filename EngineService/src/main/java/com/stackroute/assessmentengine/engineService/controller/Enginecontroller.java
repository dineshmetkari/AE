package com.stackroute.assessmentengine.engineService.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;
import com.stackroute.assessmentengine.engineService.exceptions.KafkaUnavialableException;
import com.stackroute.assessmentengine.engineService.exceptions.ResourceNotFoundException;
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
	
	
	static List<Question> questions=new ArrayList<>();
	static HashMap<Integer,Question> questionPaper;
	static HashMap<String,HashMap<Integer,Question>> usermap= new HashMap<>();


	@MessageMapping("/questions/{userId}")
    public void questionData(@DestinationVariable String userId,Question question)  {
		
		System.out.println("user:"+userId);
		Enginecontroller ec=new Enginecontroller();
		
		HashMap<Integer,Question> questionPaper1;
		questionPaper1=usermap.get(userId);
		if(questionPaper1==null)
		{
			
			System.out.println("--------------------------------------calling getall method");
			ec.getall(userId);
		}
		
		System.out.println("recieved from user"+question.toString());
		questionPaper=usermap.get(userId);
		
		
        if(questionPaper.size()!=0)
        {
		System.out.println("set of questionPaper"+questionPaper1);
		Question nextQuestion=null;
		
		try {
			
		questionRepository.addquestion(question);
		
		nextQuestion=questionRepository.getquestion(question); 
		}
		catch(ResourceNotFoundException e) {
			System.out.println("can't get it from jedis pool");
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
	                
	                if(nextQuestion.getSelectedAnswer()!=null) {
	                	questionBean1.setUserAnswer(nextQuestion.getSelectedAnswer());
	                }
	                else {
	                	questionBean1.setUserAnswer("");
	                }
	                
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
	          	    catch(KafkaUnavialableException e) {
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
                questionBean1.setUserAnswer("");
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
	        	  catch(KafkaUnavialableException e) {
	            	    	throw new KafkaUnavialableException("Kafka server is down");
	            	    }
        	      }
        
                }
        	
             }
	      catch(KafkaUnavialableException e) {
	    	  System.out.println("Kafka server is down");
	    	  
	      }
        }
	    else {
	      	System.out.println("else block");
	      	simpMessagingTemplate.convertAndSend("/topic/question/"+question.getUserid() , new QuestionBean("Exam not yet started"));
	      	
	      }
		}
    
   // @KafkaListener(topics = "${kafka.topic.json}")
    private static void getall(String user)
    {
	    	try {
	        final String uri = "http://localhost:8074/all/"+user+"/";
	        RestTemplate restTemplate = new RestTemplate();
	        System.out.println("in data");
	        Question[] forNow = restTemplate.getForObject(uri, Question[].class);
	       
	        List<Question> searchList= Arrays.asList(forNow);
	        Integer i=1;
	        String[] arr= {"true","false"};
	        questionPaper=new HashMap<>();
	        for(Question q:searchList) {
	        	if(q.getQuestionType().equalsIgnoreCase("truefalse")) {
	        	q.setOptions(arr);
	        	}
	        	q.setId(String.valueOf(i));
	        	q.setMarksAllotted("1");
	        	questionPaper.put(i, q);
	        	i++;
	        }
    	
//    List<Question> searchList= new ArrayList<>();
//    
//    String[] s= {"a","b","c","d"};
//    searchList.add(new Question("ok","1","","", "","oops", "java", "l1", "easy",
//			"mcq", "Question1","A", "1", "",s));
//    searchList.add(new Question("ok","2","","", "","oops", "java", "l1", "easy",
//			"mcq", "Question2","A", "1", "",s));
//      Integer i=1;
//      questionPaper=new HashMap<>();
//      for(Question q:searchList) {
//      	q.setId(String.valueOf(i));
//      	q.setUserid(user);
//      	q.setMarksAllotted("1");
//      	questionPaper.put(i, q);
//      	i++;
//      }
        usermap.put(user,questionPaper);
        System.out.println("User Map:"+usermap);
        questions=searchList;
        System.out.println("List of Questios:"+searchList);
	    	}
	    	catch(Exception e) {
	    		System.out.println("can't get data");
	    	}
       }
}
		
