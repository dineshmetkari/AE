package com.stackroute.assessmentengine.questionbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
<<<<<<< HEAD
=======
@EnableDiscoveryClient
>>>>>>> 7b164806914df64d0a59a51b72beb98a5b703fc0
@SpringBootApplication
public class QuestionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionBankApplication.class, args);
	}
}
