package com.stackroute.assessmentengine.evaluationmanager.message;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.web.client.RestTemplate;
import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;
import com.stackroute.assessmentengine.evaluationmanager.exception.KafkaUnavialableException;
import com.stackroute.assessmentengine.evaluationmanager.service.EvaluationManagerService;

import ch.qos.logback.core.net.SyslogOutputStream;

//@Component
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	KafkaProducer producer;
	@Autowired
	EvaluationManagerService evaluationmanager;

	
	@KafkaListener(topics="${kafka.topic.json}")
	
    public void questionrecived(QuestionBean questionBean) {
		
		log.info("Question for Evaluation:", questionBean.toString());
		try {
		producer.sendQuestion(questionBean);
		}
		catch(KafkaUnavialableException e) {
			
			System.out.println("Kafka server is Down");
		}
     
     }
	
    @KafkaListener(topics="${kafka.topic.json2}")
    
    public void examstatus(QuestionBean q)   {
    	
    	evaluationmanager.users(q.getStudentId());
    	
   }//end of examstatus method
}