package com.evaluator.EvaluatorServices.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import java.util.concurrent.CountDownLatch;
import com.evaluator.EvaluatorServices.domain.McqEvaluatorBean;
import com.evaluator.EvaluatorServices.service.McqEvaluatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaConsumer {
	//private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	McqEvaluatorService mcqEvaluatorService;
	
	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	   return latch;
	 }
	
	@KafkaListener(topics = "${kafka.topicMcq.jsonMcq}")
	 public void receive(McqEvaluatorBean mcqEvaluatorBean) {
		
	   log.info("received evaluatorBean='{}'", mcqEvaluatorBean.toString());
	   System.out.println("------------------------"+mcqEvaluatorBean.getQuestion());
	   mcqEvaluatorService.evaluateMcqQuestion(mcqEvaluatorBean);
	   latch.countDown();
	 }
}

