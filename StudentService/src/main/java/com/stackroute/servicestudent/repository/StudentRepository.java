package com.stackroute.servicestudent.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.stackroute.servicestudent.domain.StudentBean;

public interface StudentRepository extends MongoRepository<StudentBean, String>{
	
	@Query("{emailId:'?0'}")
	List<StudentBean> getStudentById(String emailId);

}
