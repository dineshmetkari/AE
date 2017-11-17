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
		
		/*// Setting Options Bean
		McqOptionsBean options = new McqOptionsBean("OOPT","OOPA","OOPS","OOPO");
		List<McqOptionsBean> optionsList = new ArrayList<>();
		optionsList.add(options);
		
		// Setting the evaluator with question bean values
		McqEvaluatorBean evaluator = new McqEvaluatorBean("1", "32", "45", "What is in java",
				optionsList, "c:OOPS", "a:OOPT", "mcq", "java", "L1", "easy", "1", "30",
				"questionEndTime", "questionEndTime", "examStartTime", "examEndTime");*/
		
		
		if(evaluatorBean.getCorrectAnswer().equals(evaluatorBean.getUserAnswer()))
		{
			evaluatorBean.setMarksAttained(evaluatorBean.getMarksAlloted());
			
		}
		else
		{
			evaluatorBean.setMarksAttained("0");
		}
		
		evaluatorBean.setQuestionFlag("true");
		
		kafkaProducer.send(evaluatorBean);
		
		return "Evaluation of MCQ done";
		
	}
	
}
