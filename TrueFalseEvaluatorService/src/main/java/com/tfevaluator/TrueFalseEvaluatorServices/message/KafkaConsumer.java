package com.tfevaluator.TrueFalseEvaluatorServices.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.tfevaluator.TrueFalseEvaluatorServices.domain.TrueFalseEvaluatorBean;
import com.tfevaluator.TrueFalseEvaluatorServices.service.TrueFalseEvaluatorService;

import java.util.concurrent.CountDownLatch;

public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	TrueFalseEvaluatorService trueFalseEvaluatorService;
	
	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	   return latch;
	 }
	
	@KafkaListener(topics = "${kafka.topicTf.jsonTf}")
	 public void receive(TrueFalseEvaluatorBean trueFalseEvaluatorBean) {
		
	   log.info("received evaluatorBean='{}'", trueFalseEvaluatorBean.toString());
	   
	   trueFalseEvaluatorService.evaluateMcqQuestion(trueFalseEvaluatorBean);
	   latch.countDown();
	 }
}

