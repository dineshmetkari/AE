package com.stackroute.serviceadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.stackroute.serviceadmin.config.SpringMongoConfig;
import com.stackroute.serviceadmin.domain.AdminBean;
import com.stackroute.serviceadmin.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService{

	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@Autowired
	AdminRepository adminRepository;
	AdminBean userBean;

		@Override
	public AdminBean updateUser(AdminBean userBean) {

		return adminRepository.save(userBean);
	}

	@Override
	public String getLoginDetails(String emailId,String password) {

		Query query11 = new Query();
		query11.addCriteria(Criteria.where("emailId").in(emailId)
				.andOperator(Criteria.where("password").in(password)));

		List<AdminBean> userTest11 = mongoOperation.find(query11, AdminBean.class);
		System.out.println("query11 - " + query11.toString());
		for (AdminBean adminlogin : userTest11) {
			System.out.println("userTest11 - " + adminlogin);
		}
		System.out.println(userTest11.toString());
		return userTest11.toString();
	}
}
