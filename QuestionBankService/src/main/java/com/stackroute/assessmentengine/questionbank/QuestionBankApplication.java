package com.stackroute.assessmentengine.questionbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class QuestionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionBankApplication.class, args);
	}
}
