package com.stackroute.assessmentengine.rediscache.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.domain.QuestionBean1;


public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String,QuestionBean1> kafkaTemplate;
	
	@Value("${kafka.topic.json}")
	String kafkaTopic;
	
	public void send(QuestionBean1 questionbean) {
	    log.info("sending data='{}' For Topic : " + kafkaTopic);
	    
	    kafkaTemplate.send(kafkaTopic,questionbean);
	}
	
}