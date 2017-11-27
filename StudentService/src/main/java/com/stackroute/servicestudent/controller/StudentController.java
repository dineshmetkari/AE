package com.stackroute.servicestudent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.stackroute.servicestudent.domain.StudentBean;
import com.stackroute.servicestudent.exception.UserAlreadyExistException;
import com.stackroute.servicestudent.service.StudentService;


@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method=RequestMethod.GET)
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<List<StudentBean>> getAllUsers() {
		
		return ResponseEntity.ok(studentService.getAllUsers());
		// ResponseEntity returns List along witidh HTTP Status.
	}
	
	@RequestMapping(method=RequestMethod.GET,value="{emailId}")
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<List<StudentBean>> getUser(@PathVariable String emailId) {
		studentService.getStudentById(emailId);
		return ResponseEntity.ok(studentService.getStudentById(emailId));
		// ResponseEntity returns message along with HTTP Status.
		
	}
	@CrossOrigin("*")
	@RequestMapping(method=RequestMethod.POST)
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<String> addUser(@RequestBody StudentBean userBean) throws UserAlreadyExistException{
		try {
			studentService.addStudent(userBean);
		}catch(UserAlreadyExistException ue){
			throw new UserAlreadyExistException("User Already Exists");
		}
		
		return ResponseEntity.ok("User saved successfully");
		// ResponseEntity returns message along with HTTP Status.
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<String> updateUser(@RequestBody StudentBean userBean) {
		
		studentService.updateStudent(userBean);
		return ResponseEntity.ok("User Updated successfully");
		// ResponseEntity returns message along with HTTP Status.
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	// This Annotation takes care to map specific response to a method with fixed value attribute
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		
		studentService.deleteStudent(id);
		 
		 return ResponseEntity.ok("User Deleted successfully");
		// ResponseEntity returns message along with HTTP Status.
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/specificusers/{emailId}")
	public String getSpecificUsers(@PathVariable String emailId) {
		
		if((studentService.getSpecUsers(emailId)).toString().equals("[]")) {
			return "User Not existed";
		}
		else {
			return "User Already existed";
		}
		
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/studentlogin/{emailId}/{password}")
	public String getLoginDetails(@PathVariable String emailId,@PathVariable String password) {
		
		try {
			if(!(studentService.getLoginDetails(emailId,password)).equals("[]")){
				return "Login Successfull";
			}else {
				return "Login Failed";
			}
		}catch(Exception e) {
			return "Login Failed";
		}	
	}
}
