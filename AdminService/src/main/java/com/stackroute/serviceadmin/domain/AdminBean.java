package com.stackroute.serviceadmin.domain;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="AdminData")
public class AdminBean {
	
	private String emailId;
	private String password;
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public AdminBean(String emailId, String password) {
		super();
		
		this.emailId = emailId;
		this.password = password;
	}
	public AdminBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	   


	
}
