package com.stackroute.assessmentengine.questionbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.stackroute.assessmentengine.questionbank.domain.QuestionBank;
import com.stackroute.assessmentengine.questionbank.exception.CustomExceptions;
import com.stackroute.assessmentengine.questionbank.service.QuestionBankService;

import java.util.*;
@RestController
@CrossOrigin("*")	
public class QuestionBankController {
	@Autowired
	private QuestionBankService questionBankService;
	@CrossOrigin("*")
	@RequestMapping("/questions")
	public ResponseEntity<List<QuestionBank>> getallQuestions() throws CustomExceptions {
		
		return ResponseEntity.ok( questionBankService.getallquestions());
		
	}
	@RequestMapping("/questions/{id}")
	public ResponseEntity<QuestionBank> getQuestion(@PathVariable String id) throws CustomExceptions{
		QuestionBank q;
			try {
				q=questionBankService.getquestion(id);
			} catch (CustomExceptions e) {
				
				throw new CustomExceptions("no question available");
			}
			return ResponseEntity.ok(q);
			
	}
	@CrossOrigin("*")
	@RequestMapping(method=RequestMethod.POST,value="/questions")
	public ResponseEntity<String> addQuestion(@RequestBody QuestionBank questionBank) {
		
		questionBankService.addquestion(questionBank);
		return ResponseEntity.ok("Question saved successfully");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/questions/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody QuestionBank question, @PathVariable String id) {
		
		questionBankService.updatequestion(id,question);
		return ResponseEntity.ok("Question Updated successfully");
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/questions/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
		
		questionBankService.deletequestion(id);
		 
		 return ResponseEntity.ok("Question Deleted successfully");
	}
	@CrossOrigin("*")
	@RequestMapping(method=RequestMethod.GET,value="/specquestions/{subject}/{topic}/{level}/{complexity}/{questionType}/{num}")
	public ResponseEntity<List<QuestionBank>> getSpecificQuestions(@PathVariable String subject,@PathVariable String topic,@PathVariable String level,@PathVariable String complexity,@PathVariable String questionType,@PathVariable String num) {
		
		return ResponseEntity.ok( questionBankService.getSpecificquestions(subject,topic,level,complexity,questionType,num));
		
	}
	
	
	

}
