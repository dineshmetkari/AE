package com.stackroute.assessmentengine.userresultservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.stackroute.assessmentengine.userresultservice.config.SpringMongoConfig;
import com.stackroute.assessmentengine.userresultservice.domain.ResultBean;
import com.stackroute.assessmentengine.userresultservice.domain.UserResultBean;
import com.stackroute.assessmentengine.userresultservice.repository.Resultmongorepository;
@Service
public class UserResultServiceImpl implements UserResultService {
	
	@Autowired
	Resultmongorepository resultmongorepository;
	
	@Autowired
    MongoTemplate mongoTemplate;
    
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongo = (MongoOperations) ctx.getBean("mongoTemplate");
    
    
	@Override
	public UserResultBean addUsers(UserResultBean userResultBean) {
		
		 int usum=0;
	     int uqm=0;
	        
	     int esum=0;
	     int eqm=0;
	        List<ResultBean> list=userResultBean.getResultBean();
	    	for(ResultBean u: list){
	    		uqm=Integer.parseInt(u.getMarksAttained());
	    		usum=usum+uqm;
	    		
	    		eqm=Integer.parseInt(u.getMarksAlloted());
	    		esum=esum+eqm;
	    		
	    	}
	    	
	    	userResultBean.setTotalMarksobtained(Integer.toString(usum));
	    	userResultBean.setTotalExamMarks(Integer.toString(esum));
		
		return resultmongorepository.save(userResultBean);
	}
	@Override
	public List<UserResultBean> getAllUsers() {
		
		return resultmongorepository.findAll();
	}
	
	 /*public List<UserResultBean> getResult(String userId) {
		 
		 	Query query = new Query();
	        query.addCriteria(Criteria.where("userId").in(userId));
	        
	        List<UserResultBean> results = mongo.find(query, UserResultBean.class);
	        //log.info("query is ========="+query.toString());
	        
	        for(UserResultBean resultBean:results)
	        {
	            //log.debug("ResultBean ========"+resultBean);
	        }
	        
	        return results;
	        
	    }*/
	public UserResultBean getResult(String userId) {
		 
	 	
        
        return resultmongorepository.findOne(userId);
        
    }
	 
	 public List<UserResultBean> getSpecificResult(String subject){
	        Query query = new Query();
	        query.addCriteria(Criteria.where("results.subject").in(subject));
	        
	        List<UserResultBean> results = mongo.find(query, UserResultBean.class);
	        //log.info("query is ========="+query.toString());
	        
	        for(UserResultBean resultBean:results)
	        {
	            //log.debug("ResultBean ========"+resultBean);
	        }
	        
	        return results;
	    }
	
	

}
