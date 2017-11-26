package com.stackroute.assessmentengine.rediscache.message;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.stackroute.assessmentengine.rediscache.domain.QuestionBean;
import com.stackroute.assessmentengine.rediscache.domain.QuestionBean1;
import com.stackroute.assessmentengine.rediscache.exceptions.KafkaUnavialableException;
import com.stackroute.assessmentengine.rediscache.exceptions.RedisServerDownException;
import com.stackroute.assessmentengine.rediscache.repository.QuestionRepository;

public class KafkaConsumer {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	KafkaProducer producer;
	
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.json1}")
  public void received(QuestionBean questionBean) throws KafkaUnavialableException, RedisServerDownException {
    LOGGER.info("received questionBEan='{}'", questionBean.toString());
    //System.out.println("+++++++++++++++++++"+questionBean.toString());
    latch.countDown();
    try {
    questionRepository.addquestion(questionBean,questionBean.getStudentId());
    }
    catch(Exception e) {
    	throw new RedisServerDownException("Redis server is Down");
    }
    QuestionBean1 q=new QuestionBean1();
  
    String s=(String) questionBean.getStudentId();
    q.setStudentId(s);
    q.setComplexity(questionBean.getComplexity());
    q.setUserAnswer(questionBean.getUserAnswer());
    q.setCorrectAnswer(questionBean.getCorrectAnswer());
    q.setQuestionStartTime(questionBean.getQuestionStartTime());
    q.setLevel(questionBean.getLevel());
    q.setMarksAllotted(questionBean.getMarksAllotted());
    q.setMarksAttained(questionBean.getMarksAttained());
    q.setQuestion(questionBean.getQuestion());
    q.setOptions(questionBean.getOptions());
    q.setQuestionId(questionBean.getQuestionId());
    q.setQuestionType(questionBean.getQuestionType());
    q.setSubject(questionBean.getSubject());
    q.setIsEvaluated(questionBean.getIsEvaluated());
    try {
    producer.send(q);
    }
    catch(Exception e) {
    	throw new KafkaUnavialableException("Kafka server is down");
    }
    System.out.println("Number of Questions: " + questionRepository.getNumberOfquestions(questionBean.getStudentId()));
    
  }
  @KafkaListener(topics = "${kafka.topic.json2}")
  public void evaluated(QuestionBean questionBean) {
    System.out.println("Evaluated Question updated"+questionBean.toString());
    
    try {
    questionRepository.addquestion(questionBean,questionBean.getStudentId());
    System.out.println("Number of Questions in repo: " + questionRepository.getNumberOfquestions(questionBean.getStudentId()));
    
    }
    catch(RedisServerDownException redisServerDownException) {
    	System.out.println("Redis server is Down");
    	
    }

  }
}
