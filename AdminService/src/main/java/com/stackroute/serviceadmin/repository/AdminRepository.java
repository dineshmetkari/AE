package com.stackroute.servicestudent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.stackroute.servicestudent.domain.StudentBean;

public interface StudentRepository extends MongoRepository<StudentBean, String>{

}
