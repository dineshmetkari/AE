package com.stackroute.serviceadmin.service;

import com.stackroute.serviceadmin.domain.AdminBean;


public interface AdminService {
	public AdminBean updateUser(AdminBean userBean);
	public String getLoginDetails(String emailId,String password);
}
