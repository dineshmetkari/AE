package com.stackroute.assessmentengine.engineService.controller;

import static org.mockito.Matchers.anyList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;
import com.stackroute.assessmentengine.engineService.message.KafkaProducer;



@RestController
public class Enginecontroller {
	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	KafkaProducer producer;
	
	
	static List<Question> questions;
	static Map<Integer,Question> questionPaper;
	String questionarr[]= {"Question1","Question2"};
	String answer[]= {"Answer1","Answer2"};

	//@CrossOrigin(origins = "http://localhost:3000")
	public String message(String msg) {
		return msg;
		
	}

	@MessageMapping("/questions/{userId}")
    
    public void questionData(@DestinationVariable String userId,Question question) throws Exception {
		System.out.println(userId);
        Thread.sleep(100); // simulated delay
		System.out.println("               "+question.toString());
		Enginecontroller ec=new Enginecontroller();
		ec.getall();
		List<String> list=new ArrayList();
		list.add("option1");
		list.add("option2");
		System.out.println("-----------------------------------"+questionPaper);
		
		QuestionBean questionBean=new QuestionBean();
		questionBean.setStudentId(question.getUserid());
		System.out.println("Question ID"+question.getId());
		questionBean.setQuestionId(question.getId());
		//questionBean.setQuestionId("4");
		questionBean.setQuestion(question.getQuestion());
		List<String> optionsList1=Arrays.asList(question.getOptions());
		questionBean.setOptions(optionsList1);
		questionBean.setCorrectAnswer(question.getCorrectAnswer());
		questionBean.setUserAnswer(question.getSelectedAnswer());
		questionBean.setQuestionType(question.getQuestionType());
		questionBean.setSubject(question.getSubject());
		questionBean.setComplexity(question.getComplexity());
		questionBean.setQuestionStartTime(question.getStartTime());
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String endtime = dateFormat1.format(date);
		questionBean.setQuestionEndTime(endtime);
		questionBean.setMarksAlloted(question.getMarksAllotted());
		questionBean.setLevel(question.getLevel());
		
		
		QuestionBean questionBean1=new QuestionBean();
		Integer n=Integer.parseInt(question.getNextQuestion());
		//System.out.println("nextquestion no:  "+n);
		Question next=questionPaper.get(n);
		questionBean1.setNoOfQuestions(String.valueOf(questionPaper.size()));
         // System.out.println("NextQuestion::::::::::::"+next);
        questionBean1.setQuestionId(String.valueOf(next.getId()));
        questionBean1.setQuestion(next.getQuestion());
        questionBean1.setQuestionType(next.getQuestionType());
        questionBean1.setMarksAlloted(next.getMarksAllotted());
        questionBean1.setSubject(next.getSubject());
        questionBean1.setComplexity(next.getComplexity());
        questionBean1.setCorrectAnswer(next.getCorrectAnswer());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String starttime = dateFormat.format(date1);
        questionBean1.setQuestionStartTime(starttime);
        questionBean1.setLevel(next.getLevel());
        List<String> optionsList=Arrays.asList(next.getOptions());
        questionBean1.setOptions(optionsList);
       if(question.getExamStatus().equalsIgnoreCase("Close"))
       {
    	   producer.sendQuestion1(new QuestionBean("Exam closed:"+question.getUserid()));
    	
       }
		
        if(questions.size()!=0)
        {
        	
        	  simpMessagingTemplate.convertAndSend("/topic/question/"+question.getUserid() ,questionBean1);
       producer.sendQuestion(questionBean);
       
        
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
        	questionPaper.put(i, q);
        	i++;
        }
        questions=searchList;
        System.out.println(searchList);
       }

}
		
