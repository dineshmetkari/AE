package com.stackroute.assessmentengine.evaluationmanager.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stackroute.assessmentengine.evaluationmanager.domain.QuestionBean;
import com.stackroute.assessmentengine.evaluationmanager.domain.UserResultBean;
import com.stackroute.assessmentengine.evaluationmanager.message.KafkaConsumer;
import com.stackroute.assessmentengine.evaluationmanager.message.KafkaProducer;

import redis.clients.jedis.Jedis;

@RestController
public class EvaluationController {
	
	
	@Autowired
	KafkaConsumer consumer;
	@Autowired
	KafkaProducer producer;
	
	RedisTemplate<String,String> template;
//	@GetMapping("/evaluation")
//	public void getKeys() {
//
//		consumer.examstatus("user1");
		
//		final Jedis jedis = new Jedis("localhost");
//		Set<String> names = jedis.keys("*");
//
//        java.util.Iterator<String> it = names.iterator();
//        String k="";
//        while(it.hasNext()) {
//            String s = it.next();
//            System.out.println("hi");
//            System.out.println(s + " : " + jedis.get(s));
//            k=s;
//        }
//        jedis.del(k);
        
//		Set<String> redisKeys = template.keys("*");
//		List<String> keysList = new ArrayList<>();
//		Iterator<String> it = redisKeys.iterator();
//		while (it.hasNext()) {
//		       String data = it.next();
//		       keysList.add(data);
//		       System.out.println("key"+data);
//		}
//		System.out.println(keysList);
//}
	@GetMapping("/data")
	public void send() {
		List<String> list=new ArrayList();
		list.add("option1");
		list.add("option2");
		
		QuestionBean questionBean=new QuestionBean("asas","user2","1","Question1",
		list,"option1", "option1","mcq","java","l1","easy","2","10",
		"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
		"0","false");
		QuestionBean questionBean1=new QuestionBean("asas","user2","2","Question1",
		list,"option1", "option1","mcq","java","l1","easy","2","10",
		"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
		"0","false");
		
		List<QuestionBean> questiobeanList=new ArrayList();
		questiobeanList.add(questionBean);
		questiobeanList.add(questionBean1);
		System.out.println("kahjahdjahjdsahsahj");
		
		
//RestTemplate rt = new RestTemplate();
//		
//        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        rt.getMessageConverters().add(new StringHttpMessageConverter());
//        String uri_results= new String("http://172.23.238.205:8085/results");
//		
//        UserResultBean userexam = new UserResultBean();
//
//       userexam.setUserId("user1");
//       userexam.setResultBean(questiobeanList);
//
//      // UserResultBean returns = rt.postForObject(uri, u, User.class, vars);
//      UserResultBean returns = rt.postForObject(uri_results, userexam,  UserResultBean.class);
       
   	UserResultBean userexam = new UserResultBean();
	
	  userexam.setUserId("user2");
	  userexam.setResultBean(questiobeanList);
	  producer.sendUserExam(userexam);

      
	}
//	@GetMapping("/test")
//	public void test() throws InterruptedException{
//		consumer.questionrecived("user1");
//		
//		List<String> list=new ArrayList();
//		list.add("option1");
//		list.add("option2");
//		
//		QuestionBean questionBean=new QuestionBean("asas","user1","1","Question1",
//		list,"option1", "option1","mcq","java","l1","easy","2","10",
//		"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
//		"0","false");
//		QuestionBean questionBean1=new QuestionBean("asas","user1","2","Question1",
//		list,"option1", "option1","mcq","java","l1","easy","2","10",
//		"2011-12-31 07:11:01","2011-12-31 07:11:50","2011-12-31 07:00:01", "2011-12-31 10:00:01",
//		"0","false");
//		
//		consumer.examstatus(questionBean);
//		
//	}
}
