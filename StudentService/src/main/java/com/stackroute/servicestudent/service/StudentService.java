package com.stackroute.servicestudent.service;

import java.util.List;

import com.stackroute.servicestudent.domain.StudentBean;
import com.stackroute.servicestudent.exception.UserAlreadyExistException;


public interface StudentService {
	public List<StudentBean> getAllUsers();
	public List<StudentBean> getStudentById(String emailId);
	public StudentBean addStudent(StudentBean userBean) throws UserAlreadyExistException;
	public StudentBean updateStudent(StudentBean userBean);
	public String deleteStudent(String id);
	public List getSpecUsers(String emailId);
	public String getLoginDetails(String emailId,String password);
}
