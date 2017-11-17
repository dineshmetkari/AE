package com.stackroute.servicestudent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public class SpringMongoConfig{
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),"sample");
		return mongoTemplate;

	}

}
