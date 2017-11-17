package com.stackroute.servicestudent.service;

import java.util.List;

import com.stackroute.servicestudent.domain.StudentBean;
import com.stackroute.servicestudent.exception.UserAlreadyExistException;


public interface StudentService {
	public List<StudentBean> getAllUsers();
	public StudentBean getUserById(String id);
	public StudentBean addUser(StudentBean userBean) throws UserAlreadyExistException;
	public StudentBean updateUser(StudentBean userBean);
	public String deleteUser(String id);
	public String getLoginDetails(String emailId,String password);
}
