package com.stackroute.assessmentengine.questionbank.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;



@Document(collection = "questionBank")
public class QuestionBank {
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdDate=new Date();
	private List<SubjectList> subjectLists;
	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<SubjectList> getSubjectLists() {
		return subjectLists;
	}

	public void setSubjectLists(List<SubjectList> subjectLists) {
		this.subjectLists = subjectLists;
	}

	public QuestionBank(List<SubjectList> subjectLists) {
		super();
		this.subjectLists = subjectLists;
	}

	public QuestionBank() {
		super();
		// TODO Auto-generated constructor stub
		this.subjectLists=new ArrayList<>();
	}
	
}
