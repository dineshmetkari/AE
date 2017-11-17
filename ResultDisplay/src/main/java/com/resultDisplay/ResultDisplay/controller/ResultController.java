package com.resultDisplay.ResultDisplay.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resultDisplay.ResultDisplay.domain.ResultBean;
import com.resultDisplay.ResultDisplay.service.ResultServiceImpl;

@RestController
@RequestMapping(value = "/result")
public class ResultController {
	
	@Autowired
	private ResultServiceImpl ResultService;
	
	@RequestMapping("/users")
	public ResponseEntity<List<ResultBean>> getAllResults() {
		
		return ResponseEntity.ok( ResultService.getAllUsers());
		
	}
	@RequestMapping("/user/{id}")
	public ResponseEntity<ResultBean> getResult(@PathVariable String id) {
		
		return ResponseEntity.ok(ResultService.getResult(id));
		
	}
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public ResponseEntity<String> addResult(@RequestBody ResultBean resultBean) {
		
		ResultService.addResult(resultBean);
		return ResponseEntity.ok("User added successfully");
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/specuser/{subject}")
	public ResponseEntity<List<ResultBean>> getSpecificResults(@PathVariable String subject) {
		
		return ResponseEntity.ok( ResultService.getSpecificResult(subject));
		
	}
	

}

