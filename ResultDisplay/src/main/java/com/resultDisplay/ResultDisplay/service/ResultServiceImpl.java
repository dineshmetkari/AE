package com.resultDisplay.ResultDisplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.resultDisplay.ResultDisplay.config.MongoConfig;
import com.resultDisplay.ResultDisplay.domain.ResultBean;
import com.resultDisplay.ResultDisplay.repository.ResultRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService{
	
	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongo = (MongoOperations) ctx.getBean("mongoTemplate");
	
	public List<ResultBean> getAllUsers() {
		
		return resultRepository.findAll();
		
	}
	
	public ResultBean getResult(String id) {
		return resultRepository.findOne(id);
		
	}
	
	public String addResult(ResultBean resultBean) {
		resultRepository.save(resultBean);
		return "Result Added";
	}
	
	public List<ResultBean> getSpecificResult(String subject){
		Query query = new Query();
		query.addCriteria(Criteria.where("subject").in(subject));
		
		List<ResultBean> results = mongo.find(query, ResultBean.class);
		log.info("query is ========="+query.toString());
		
		for(ResultBean resultBean:results)
		{
			log.debug("ResultBean ========"+resultBean);
		}
		
		return results;
	}

}
