package com.resultService.ResultManager.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.web.client.RestTemplate;

import com.resultService.ResultManager.domain.QuestionBean;
import java.util.concurrent.CountDownLatch;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaConsumer {
	
	@Autowired
	KafkaProducer kafkaProducer;
		
	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	   return latch;
	 }
	
	@KafkaListener(topics = "${kafka.topic.json}")
	 public void receive(QuestionBean questionBean) {
		
	   log.info("received questionBean='{}'", questionBean.toString());
	   System.out.println("------------------------"+questionBean.getQuestion());
	   
	   kafkaProducer.send(questionBean);
	   
//	   // If to send using Rest Template
//	   final String uri = "http://172.23.238.217:8074/all";
//       RestTemplate restTemplate = new RestTemplate();
//       System.out.println("in data");
//       restTemplate.put(uri, questionBean);
	   
	   latch.countDown();
	 }
}

