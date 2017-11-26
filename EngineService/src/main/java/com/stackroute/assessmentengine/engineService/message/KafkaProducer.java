package com.stackroute.assessmentengine.engineService.message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;
import com.stackroute.assessmentengine.engineService.exceptions.KafkaUnavialableException;


public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);



  @Value("${kafka.topic.json1}")
  private String jsonTopic1;

  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate1;


  @Value("${kafka.topic.json}")
  private String jsonTopic;

  @Autowired
  private KafkaTemplate<String, QuestionBean> kafkaTemplate;
  

  public void sendQuestion1(QuestionBean questionBean) throws KafkaUnavialableException {
	    LOGGER.info("sending QuestionBean='{}' For topic"+jsonTopic1+"", questionBean.toString());
	    try {
	    kafkaTemplate1.send(jsonTopic1, questionBean);
	    }
	    catch(Exception e){
	    	throw new KafkaUnavialableException("kafka server is down");
	    }
	  }

  public void sendQuestion(QuestionBean questionBean) throws KafkaUnavialableException {
	    LOGGER.info("sending QuestionBean='{}'for topic"+""+jsonTopic+"", questionBean.toString());
	    try {
	    kafkaTemplate.send(jsonTopic, questionBean);
	    }
	    catch(Exception e){
	    	throw new KafkaUnavialableException("kafka server is down");
	    }
	  }
	  
}
