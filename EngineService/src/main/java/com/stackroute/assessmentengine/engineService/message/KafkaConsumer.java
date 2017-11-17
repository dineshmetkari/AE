package com.stackroute.assessmentengine.engineService.message;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import com.stackroute.assessmentengine.engineService.domain.Question;
import com.stackroute.assessmentengine.engineService.domain.QuestionBean;

public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

//  @KafkaListener(topics = "${kafka.topic.json}")
//  public void receive(Question question) {
//    LOGGER.info("received question='{}'", question.toString());
//    System.out.println("+++++++++++++++++++"+question.toString());
//    latch.countDown();
//  }
 // @KafkaListener(topics = "${kafka.topic.json}")
  public void received(QuestionBean questionBean) {
    LOGGER.info("received questionBEan='{}'", questionBean.toString());
    System.out.println("+++++++++++++++++++"+questionBean.toString());
    latch.countDown();
  }
}
