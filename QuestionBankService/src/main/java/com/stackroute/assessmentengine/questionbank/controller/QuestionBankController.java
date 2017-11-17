package com.stackroute.assessmentengine.questionbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mongodb.AggregationOutput;
import com.mongodb.DBCollection;
import com.stackroute.assessmentengine.questionbank.domain.QuestionBank;
import com.stackroute.assessmentengine.questionbank.service.QuestionBankService;

import java.util.*;
@RestController

public class QuestionBankController {
	@Autowired
	private QuestionBankService questionBankService;
	
	@RequestMapping("/questions")
	public ResponseEntity<List<QuestionBank>> getallQuestions() {
		
		return ResponseEntity.ok( questionBankService.getallquestions());
		
	}
	@RequestMapping("/questions/{id}")
	public ResponseEntity<QuestionBank> getQuestion(@PathVariable String id) {
		
		return ResponseEntity.ok(questionBankService.getquestion(id));
		
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
