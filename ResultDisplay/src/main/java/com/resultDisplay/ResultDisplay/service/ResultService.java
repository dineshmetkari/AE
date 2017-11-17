package com.resultDisplay.ResultDisplay.service;

import java.util.List;

import com.resultDisplay.ResultDisplay.domain.ResultBean;

public interface ResultService {
	
	public List<ResultBean> getAllUsers();
	
	public ResultBean getResult(String id);
	
	public String addResult(ResultBean resultBean);
	
	public List<ResultBean> getSpecificResult(String subject);

}
