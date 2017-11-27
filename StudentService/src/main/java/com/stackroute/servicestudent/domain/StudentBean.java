package com.stackroute.servicestudent.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="StudentData")
public class StudentBean {
	
	private String studentName;
	@Id
	private String emailId;
	private String contactNumber;
	private String skillSet;
	private String address;
	private String password;

	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public StudentBean(String studentName, String emailId, String contactNumber, String skillSet,
			String address, String password) {
		super();
		
		this.studentName = studentName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.skillSet = skillSet;
		this.address = address;
		this.password = password;
		
	}
	public StudentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	   


	
}
