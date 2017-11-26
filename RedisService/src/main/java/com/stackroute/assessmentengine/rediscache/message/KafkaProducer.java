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
import com.stackroute.assessmentengine.rediscache.exceptions.KafkaUnavialableException;


public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String,QuestionBean1> kafkaTemplate;
	
	@Value("${kafka.topic.json}")
	String kafkaTopic;
	
	public void send(QuestionBean1 questionbean) throws KafkaUnavialableException {
	    log.info("sending data='{}' For Topic : " + kafkaTopic);
	    try {
	    kafkaTemplate.send(kafkaTopic,questionbean);
	    }
	    catch(Exception e) {
	    	throw new KafkaUnavialableException("Kafka server is Down");
	    }
	}
	
}