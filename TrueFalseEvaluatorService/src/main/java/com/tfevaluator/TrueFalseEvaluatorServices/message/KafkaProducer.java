package com.tfevaluator.TrueFalseEvaluatorServices.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.tfevaluator.TrueFalseEvaluatorServices.domain.TrueFalseEvaluatorBean;


public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, TrueFalseEvaluatorBean> kafkaTemplate;

	@Value("${kafka.topic.json}")
	private String kafkaTopic;

	public void send(TrueFalseEvaluatorBean mcqEvaluatorBean) {
		log.info("sending evaluatorBean='{}'", mcqEvaluatorBean.toString());
		
		kafkaTemplate.send(kafkaTopic, mcqEvaluatorBean);
	}

}
