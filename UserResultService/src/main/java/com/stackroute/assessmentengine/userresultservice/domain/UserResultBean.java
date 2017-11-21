package com.stackroute.assessmentengine.userresultservice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="UserResults")
public class UserResultBean {
	@Id
	private String userId;
	private String totalMarksobtained;
	private String totalExamMarks;
	public String getTotalExamMarks() {
		return totalExamMarks;
	}
	public void setTotalExamMarks(String totalExamMarks) {
		this.totalExamMarks = totalExamMarks;
	}
	private List<ResultBean> resultBean;
	
	public String getTotalMarksobtained() {
		return totalMarksobtained;
	}
	public void setTotalMarksobtained(String totalMarksobtained) {
		this.totalMarksobtained = totalMarksobtained;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ResultBean> getResultBean() {
		return resultBean;
	}
	public void setResultBean(List<ResultBean> resultBean) {
		this.resultBean = resultBean;
	}
	public UserResultBean(String userId,String totalMarksobtained,String totalExamMarks, List<ResultBean> resultBean) {
		super();
		this.userId = userId;
		this.totalMarksobtained=totalMarksobtained;
		this.totalExamMarks=totalExamMarks;
		this.resultBean = resultBean;
	}
	public UserResultBean() {
		super();
		this.resultBean=new ArrayList<>();
	}
	@Override
	public String toString() {
		return "UserResultBean [userId=" + userId + ",totalMarksobtained=\" + totalMarksobtained + \", resultBean=" + resultBean + "]";
	}
	
	
	
	

	

}
