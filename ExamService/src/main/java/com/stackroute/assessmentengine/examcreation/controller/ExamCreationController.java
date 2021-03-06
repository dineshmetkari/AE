package com.stackroute.assessmentengine.examcreation.controller;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.stackroute.assessmentengine.examcreation.domian.CreateExam;
import com.stackroute.assessmentengine.examcreation.domian.QuestionPaper;
import com.stackroute.assessmentengine.examcreation.service.ExamCreationService;

@RestController
@CrossOrigin("*")
public class ExamCreationController 
{
	@Autowired
	ExamCreationService examCreationService;
	
//Storing questions manually without interaction with question bank service.Option use postman.
	@CrossOrigin("*")
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public ResponseEntity<String> createQuestionPaper(@RequestBody QuestionPaper questionPaper)
	{
		examCreationService.createQuestionPaper(questionPaper);
		return ResponseEntity.ok("Questions Saved Successfully ...!");
		
	}
//Assigning students to the question paper
	@CrossOrigin("*")
	@RequestMapping(method=RequestMethod.POST, value="/students")
	public ResponseEntity<String> createExam(@RequestBody CreateExam createExam)
	{
		examCreationService.createExam(createExam);
		return ResponseEntity.ok("Students Saved in successfully ...!");
		
	}
	@RequestMapping(method=RequestMethod.GET, value="/allStudents")
	public ResponseEntity<List<CreateExam>> getAllStudents()
	{
		return ResponseEntity.ok(examCreationService.getAllStudents());
		
	}
	
//Validating the user authorization. based on user data from student database
	@RequestMapping(method=RequestMethod.GET, value="/all/{userId}")
	public ResponseEntity<List<QuestionPaper>> getAll(@PathVariable String userId)
	{
		
		String user=userId;
		String paper="";
		List<CreateExam> list=examCreationService.getAllStudents();
		String[] students;
		for(CreateExam e:list) {
			students=e.getStudentsName();
			for(int i=0;i<students.length;i++) {
				if(user.equalsIgnoreCase(students[i]))
				{
					paper=e.getExamName();
					
				}
			}
			
		}
		
		if(paper.length()>0) {
			System.out.println("Ok Done");
			System.out.println(paper);
			return ResponseEntity.ok(examCreationService.getByOne(paper));
			
		}
		else {
			List<QuestionPaper> l=new ArrayList<>();
			return ResponseEntity.ok(l);
			
		}
		
		
	}
//You can to see all question papers from  here
	@RequestMapping(method=RequestMethod.GET, value="/all")
	public ResponseEntity<List<QuestionPaper>> getAll()
	{
		return ResponseEntity.ok(examCreationService.getAll());
		
	}
	
//If you want particular question paper then go here
	@RequestMapping(method=RequestMethod.GET, value="/getstudent/{examName}")
	public ResponseEntity<List<CreateExam> >getQuestionPaperName(@PathVariable String examName)
	{
		return ResponseEntity.ok(examCreationService.getQuestionPaperName(examName	));
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/paper/{questionId}")
	public ResponseEntity<List<QuestionPaper>> getByOne(@PathVariable String questionId)
	{
		return ResponseEntity.ok(examCreationService.getByOne(questionId));
		
	}
// Getting data from question bank service and string into exam service database
	@RequestMapping(method=RequestMethod.GET , value="/all/{subject}/{topic}/{level}/{complexity}/{questionType}/{num}")
	private static void getEmployees(@PathVariable String subject,@PathVariable String topic,@PathVariable String level,@PathVariable String complexity,@PathVariable String questionType,@PathVariable String num)
	{
		
	    final String uri = "http://localhost/specquestions/"+subject+"/"+topic+"/"+level+"/"+complexity+"/"+questionType+"/"+num;

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println(result);
	}

}





