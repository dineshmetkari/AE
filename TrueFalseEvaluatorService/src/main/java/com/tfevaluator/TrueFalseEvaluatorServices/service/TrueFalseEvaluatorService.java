package com.tfevaluator.TrueFalseEvaluatorServices.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfevaluator.TrueFalseEvaluatorServices.domain.TrueFalseEvaluatorBean;
import com.tfevaluator.TrueFalseEvaluatorServices.message.KafkaProducer;


@Service
public class TrueFalseEvaluatorService {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	public String evaluateMcqQuestion(TrueFalseEvaluatorBean evaluatorBean) {
		
		if(evaluatorBean.getCorrectAnswer().equalsIgnoreCase(evaluatorBean.getUserAnswer()))
		{
			evaluatorBean.setMarksAttained(evaluatorBean.getMarksAllotted());
		}
		else
		{
			evaluatorBean.setMarksAttained("0");
		}
		
		evaluatorBean.setIsEvaluated("true");
		
		kafkaProducer.send(evaluatorBean);
		
		return "Evaluation OF True False done";
		
	}
	
}
