package com.stackroute.assessmentengine.evaluationrouter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.assessmentengine.evaluationrouter.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationrouter.message.KafkaProducer;

@RestController
@RequestMapping(value="/kafka")
public class WebRestController {
	
	@Autowired
	KafkaProducer producer;
	
	
	@GetMapping(value="/producer")
	public String producer(){
		List<String> list=new ArrayList();
		list.add("option1");
		list.add("option2");
		QuestionBean questionBean=new QuestionBean("asas","user1","1","Question1",
				list,"option1", "option1","mcq","java","l1","easy","2","10",
				"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
				null,"false");
		producer.sendQuestion(questionBean);
		return "Done";
	}
	
	
}