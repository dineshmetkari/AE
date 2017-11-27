package com.stackroute.servicestudent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.stackroute.servicestudent.config.SpringMongoConfig;
import com.stackroute.servicestudent.domain.StudentBean;
import com.stackroute.servicestudent.exception.UserAlreadyExistException;
import com.stackroute.servicestudent.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{
	
	

	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Autowired
	MongoTemplate mongoTemplate;

	
	@Autowired
	StudentRepository studentRepository;
	StudentBean userBean;

	@Override
	public List<StudentBean> getAllUsers() {
		
		return (List<StudentBean>) studentRepository.findAll();
	}

	@Override
	public List<StudentBean> getStudentById(String emailId) {
		
		return (List<StudentBean>) studentRepository.getStudentById(emailId);
	}

	@Override
	public StudentBean addStudent(StudentBean userBean) throws UserAlreadyExistException{
		
			
	
			return studentRepository.save(userBean);
		}	
		
	

	@Override
	public StudentBean updateStudent(StudentBean userBean) {
		
		return studentRepository.save(userBean);
	}

	@Override
	public String deleteStudent(String id) {
		studentRepository.delete(id);
		return "";
	}
	
	
	@Override
	public List getSpecUsers(String emailId) {
		
		Query query11 = new Query();
		query11.addCriteria(Criteria.where("emailId").in(emailId));
	
		List<StudentBean> userTest11 = mongoOperation.find(query11, StudentBean.class);
		System.out.println("query11 - " + query11.toString());
		for (StudentBean studentBean : userTest11) {
			System.out.println("userTest11 - " + studentBean);
		}
		
		return userTest11;
	}
	
	@Override
	public String getLoginDetails(String emailId,String password) {

		Query query11 = new Query();
		query11.addCriteria(Criteria.where("emailId").in(emailId)
				.andOperator(Criteria.where("password").in(password)));

		List<StudentBean> userTest11 = mongoOperation.find(query11, StudentBean.class);
		//System.out.println("query11 - " + query11.toString());
		for (StudentBean syudentlogin : userTest11) {
			//System.out.println("userTest11 - " + questionBank);
		}
		//System.out.println(userTest11.toString());
		return userTest11.toString();
	}
	

}
