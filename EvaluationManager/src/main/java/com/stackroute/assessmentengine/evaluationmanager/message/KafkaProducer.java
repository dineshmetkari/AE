package com.stackroute.assessmentengine.evaluationmanager.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;

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
  
  public void sendQuestion(QuestionBean questionBean) {
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate.send(jsonTopic, questionBean);
	  }
  public void sendUserExam(UserResultBean userResultBean) {
	    LOGGER.info("sending QuestionBean='{}'", userResultBean.toString());
	    kafkaTemplate1.send(jsonTopic1, userResultBean);
	  }
	  
}
