package com.resultService.ResultManager.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.resultService.ResultManager.domain.QuestionBean;


public class KafkaProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, QuestionBean> kafkaTemplate;

	@Value("${kafka.topic.json}")
	private String kafkaTopic;

	public void send(QuestionBean questionBean) {
		log.info("sending questionBean='{}'", questionBean.toString());
		
		kafkaTemplate.send(kafkaTopic, questionBean);
	}

}
