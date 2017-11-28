package com.stackroute.serviceadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.serviceadmin.domain.AdminBean;
import com.stackroute.serviceadmin.service.AdminService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method=RequestMethod.PUT, value="{emailId}")
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<String> updateUser(@RequestBody AdminBean userBean) {
		
		adminService.updateUser(userBean);
		return ResponseEntity.ok("User Updated successfully");
		// ResponseEntity returns message along with HTTP Status.
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/adminlogin/{emailId}/{password}")
	public String getLoginDetails(@PathVariable String emailId,@PathVariable String password) {
		
		try {
			if(!(adminService.getLoginDetails(emailId,password)).equals("[]")){
				return "Login Successfull";
			}else {
				return "Login Failed";
			}
		}catch(Exception e) {
			return "Login Failed";
		}	
	}
}
