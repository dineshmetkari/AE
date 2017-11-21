package com.evaluator.EvaluatorServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evaluator.EvaluatorServices.domain.McqEvaluatorBean;
import com.evaluator.EvaluatorServices.message.KafkaProducer;


@Service
public class McqEvaluatorService {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	public String evaluateMcqQuestion(McqEvaluatorBean evaluatorBean) {
		
		if(evaluatorBean.getCorrectAnswer().equals(evaluatorBean.getUserAnswer()))
		{
			evaluatorBean.setMarksAttained(evaluatorBean.getMarksAlloted());
			
		}
		else
		{
			evaluatorBean.setMarksAttained("0");
		}
		
		evaluatorBean.setIsEvaluated("true");
		
		kafkaProducer.send(evaluatorBean);
		
		return "Evaluation of MCQ done";
		
	}
	
}
