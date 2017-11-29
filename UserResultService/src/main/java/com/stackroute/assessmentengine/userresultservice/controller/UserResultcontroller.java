package com.stackroute.assessmentengine.userresultservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.assessmentengine.userresultservice.domain.UserResultBean;
import com.stackroute.assessmentengine.userresultservice.service.UserResultService;
@RestController
@CrossOrigin("*")
public class UserResultcontroller {
	@Autowired
	UserResultService userResultService;
	
	@RequestMapping(value="/results")
	public List<UserResultBean> getUserResult(){
		return userResultService.getAllUsers();
		
	}
	
	 /*@RequestMapping("/results/{userId}")
	    public ResponseEntity<List<UserResultBean>> getResult(@PathVariable String userId) {
	        
	        return ResponseEntity.ok(userResultService.getResult(userId));
	        
	    }*/
	
	@RequestMapping("/results/{userId}")
    public ResponseEntity<UserResultBean> getResult(@PathVariable String userId) {
        
        return ResponseEntity.ok(userResultService.getResult(userId));
        
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/results")
	public ResponseEntity<String> addUsersResult(@RequestBody UserResultBean userResultBean){
		
		userResultService.addUsers(userResultBean);
		return ResponseEntity.ok("UserResult added successfully");
		
	}
	
	 @RequestMapping(method=RequestMethod.GET,value="/results/specuser/{subject}")
	    public ResponseEntity<List<UserResultBean>> getSpecificResults(@PathVariable String subject) {
	        
	        return ResponseEntity.ok( userResultService.getSpecificResult(subject));
	        
	    }

}
