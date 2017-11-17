package com.stackroute.assessmentengine.engineService.message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;


public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);



  @Value("${kafka.topic.json}")
  private String jsonTopic1;

  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate1;

  @Value("${kafka.topic.json}")
  private String jsonTopic;

  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate;
  

  public void sendQuestion1(QuestionBean questionBean) {
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate.send(jsonTopic1, questionBean);
	  }

  public void sendQuestion(QuestionBean questionBean) {
	    LOGGER.info("sending QuestionBean='{}'", questionBean.toString());
	    kafkaTemplate1.send(jsonTopic1, questionBean);
	  }
	  
}
