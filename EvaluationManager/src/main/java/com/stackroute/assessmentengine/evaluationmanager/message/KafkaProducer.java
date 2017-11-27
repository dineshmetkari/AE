package com.stackroute.assessmentengine.evaluationmanager.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;
import com.stackroute.assessmentengine.evaluationmanager.exception.KafkaUnavialableException;

public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

  @Value("${kafka.topic.json1}")
  private String jsonTopic;
  
  @Value("${kafka.topic.json3}")
  private String jsonTopic1;


  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate;

  @Autowired
  private KafkaTemplate<String, UserResultBean> kafkaTemplate1;
  
  public void sendQuestion(QuestionBean questionBean) throws KafkaUnavialableException {
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    try {
	    kafkaTemplate.send(jsonTopic, questionBean);
	    }
	    catch(Exception e) {
	    	throw new KafkaUnavialableException("Kafka server is Down");
	    }
	  }
  public void sendUserExam(UserResultBean userResultBean) throws KafkaUnavialableException {
	    LOGGER.info("sending QuestionBean='{}'", userResultBean.toString());
	    try {
	    kafkaTemplate1.send(jsonTopic1, userResultBean);
	    }
	    catch(Exception e) {
	    	throw new KafkaUnavialableException("Kafka server is Down");
	    }
	  }
	  
}
