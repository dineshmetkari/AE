package com.stackroute.assessmentengine.evaluationrouter.message;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import com.stackroute.assessmentengine.evaluationrouter.domain.QuestionBean;


public class KafkaConsumer {
	@Autowired
	KafkaProducer producer;
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.json}")
  public void received(QuestionBean questionBean) {
    LOGGER.info("received questionBEan='{}'", questionBean.toString());
    //System.out.println("+++++++++++++++++++"+questionBean.toString());
    latch.countDown();
    if(questionBean.getQuestionType().equalsIgnoreCase("mcq")) {
    	producer.sendMcQuestion(questionBean);
    }
    else {
    	producer.sendTFQuestion(questionBean);
    }
  }
}
