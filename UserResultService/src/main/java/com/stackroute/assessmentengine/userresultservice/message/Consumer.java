package com.stackroute.assessmentengine.userresultservice.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.web.client.RestTemplate;

import com.stackroute.assessmentengine.userresultservice.controller.UserResultcontroller;
import com.stackroute.assessmentengine.userresultservice.domain.ResultBean;
import com.stackroute.assessmentengine.userresultservice.domain.UserResultBean;

import java.util.List;
import java.util.concurrent.CountDownLatch;





public class Consumer {
    @Autowired
   UserResultcontroller userResultcontroller;
        
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
       return latch;
     }
    
    @KafkaListener(topics = "${kafka.topic3.json3}")
     public void receive(UserResultBean userResultBean) {
    	
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
    	
       //log.info("received questionBean='{}'", resultBean.toString());
       System.out.println("------------------------"+userResultBean.toString());
       userResultcontroller.addUsersResult(userResultBean);
       
//       kafkaProducer.send(resultBean);
       
//       // If to send using Rest Template
//       final String uri = "http://172.23.238.217:8074/all";
//       RestTemplate restTemplate = new RestTemplate();
//       System.out.println("in data");
//       restTemplate.put(uri, questionBean);
       
       latch.countDown();
     }

	private void function(Object object) {
		// TODO Auto-generated method stub
		
	}
}
