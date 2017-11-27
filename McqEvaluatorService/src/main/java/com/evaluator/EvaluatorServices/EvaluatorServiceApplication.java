package com.evaluator.EvaluatorServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class EvaluatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluatorServiceApplication.class, args);
	}
}
