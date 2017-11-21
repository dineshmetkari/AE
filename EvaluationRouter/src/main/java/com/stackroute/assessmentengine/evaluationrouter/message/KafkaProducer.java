package com.stackroute.assessmentengine.evaluationrouter.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.stackroute.assessmentengine.evaluationrouter.domain.QuestionBean;

public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
  @Value("${kafka.topic.json}")
  private String json;
  
  @Value("${kafka.topic.json1}")
  private String jsonTopic;
  
  @Value("${kafka.topic.json2}")
  private String jsonTopic1;

  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate1;

  public void sendQuestion(QuestionBean questionBean) {
	  System.out.println("sending Question");
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate1.send(json, questionBean);
	  }
  public void sendMcQuestion(QuestionBean questionBean) {
	  System.out.println("sending MCQQuestion");
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate1.send(jsonTopic, questionBean);
	  }
  public void sendTFQuestion(QuestionBean questionBean) {
	  System.out.println("sending TFQuestion");
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate1.send(jsonTopic1, questionBean);
	  }
	  
}
