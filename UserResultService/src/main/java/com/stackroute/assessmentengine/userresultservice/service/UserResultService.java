package com.stackroute.assessmentengine.userresultservice.service;

import java.util.List;

import com.stackroute.assessmentengine.userresultservice.domain.UserResultBean;

public interface UserResultService {
	public UserResultBean addUsers(UserResultBean userResultBean);
	public List<UserResultBean> getAllUsers();
	public List<UserResultBean> getSpecificResult(String subject);
	//public List<UserResultBean> getResult(String userId);
	public UserResultBean getResult(String userId);

}
